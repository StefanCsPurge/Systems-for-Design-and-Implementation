import {Component, OnInit, ViewChild} from '@angular/core';
import {AccessoryService} from "../shared/accessory.service";
import {Location} from "@angular/common";
import {NgForm} from "@angular/forms";
import {Accessory} from "../shared/accessory.model";
import {Gun} from "../../guns/shared/gun.model";
import {GunService} from "../../guns/shared/gun.service";

@Component({
  selector: 'app-accessory-new',
  templateUrl: './accessory-new.component.html',
  styleUrls: ['./accessory-new.component.css']
})
export class AccessoryNewComponent implements OnInit {

  guns: Gun[];
  selectedGun: Gun;
  constructor(private accessoriesService: AccessoryService,
              private gunService: GunService,
              private location: Location) { }

  ngOnInit(): void {
    this.gunService.getGuns()
      .subscribe(guns => this.guns = guns);
  }

  @ViewChild('f') form: NgForm;

  onClear() {
    this.form.reset();
  }

  onSubmit(form: NgForm) {
    if(form.value.name && form.value.price && form.value.price>=0
      && form.value.type && this.selectedGun) {

      const acc:Accessory = <Accessory>{
        name: form.value.name,
        type: form.value.type,
        company: form.value.company,
        price: form.value.price,
        gunId: this.selectedGun.id};

      this.accessoriesService.saveAccessory(acc).subscribe(acc => {
        console.log("saved accessory: ", acc);
        this.location.back();
      });
    }
    else alert("Invalid accessory data!");
  }

}
