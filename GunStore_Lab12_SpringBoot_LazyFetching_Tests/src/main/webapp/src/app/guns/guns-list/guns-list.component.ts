import { Component, OnInit } from '@angular/core';
import {Gun} from "../shared/gun.model";
import {GunService} from "../shared/gun.service";
import {forkJoin, Observable} from "rxjs";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-guns-list',
  templateUrl: './guns-list.component.html',
  styleUrls: ['./guns-list.component.css']
})
export class GunsListComponent implements OnInit {
  guns: Gun[];
  selectedGun: Gun;
  updateForm: FormGroup;

  constructor(private gunService: GunService) { }

  ngOnInit(): void {
    this.gunService.getGuns()
      .subscribe(guns => this.guns = guns); // non-blocking (observable)
  }

  deleteSelectedGuns() {
    if (confirm("Are you sure you want to delete the selected guns?")) {
      let selectedGuns = this.guns
        .filter(opt => opt.checked)
        .map(opt => opt.id);

      let observables: Observable<any>[] = [];
      for (let id of selectedGuns)
        observables.push(this.gunService.deleteGun(id));
      forkJoin(observables).subscribe(_ => this.ngOnInit());
    }
  }

  updateGunForm(gun: Gun) {
      if(this.selectedGun == undefined || this.selectedGun != gun) {
        this.selectedGun = gun;
        this.initForm();
      }
      else this.selectedGun = undefined;
  }

  deleteGun(id: number) {
    if (confirm("Are you sure you want to delete the gun with ID " + id + " ?"))
      this.gunService.deleteGun(id).subscribe(_ => this.ngOnInit());
  }

  private initForm() {
    this.updateForm = new FormGroup({
      'model': new FormControl(this.selectedGun.model, Validators.required),
      'manufacturer': new FormControl(this.selectedGun.manufacturer),
      'type': new FormControl(this.selectedGun.type),
      'weight': new FormControl(this.selectedGun.weight, [Validators.required, Validators.min(0)]),
      'price': new FormControl(this.selectedGun.price, [Validators.required, Validators.min(0)])
    });
  }

  onUpdateSubmit() {
      if(this.updateForm.get('model').valid && this.updateForm.get('weight').valid && this.updateForm.get('price').valid)
      {
        const gun:Gun = <Gun>{id: this.selectedGun.id,
          model: this.updateForm.get('model').value,
          manufacturer: this.updateForm.get('manufacturer').value,
          type: this.updateForm.get('type').value,
          weight: this.updateForm.get('weight').value,
          price: this.updateForm.get('price').value,
          timesSold: 0};

        this.gunService.updateGun(gun).subscribe(gun => {
          console.log("updated gun: ", gun);
          this.ngOnInit();
        });
      }
      else alert("Invalid gun details !");
  }

  onClear() {
    this.updateForm.reset();
  }

  sortByPrice() {
    this.gunService.getGunsSortedByPrice()
      .subscribe(guns => this.guns = guns);
  }

  filterGunsByModel(value: string) {
    // JPA filter
    if(value.length==0 || value === "*")
      this.ngOnInit();
    else
      this.gunService.filterGunsByModel(value)
        .subscribe(guns => this.guns = guns);
  }
}
