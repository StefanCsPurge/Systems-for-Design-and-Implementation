import {Component, OnInit, ViewChild} from '@angular/core';
import {AmmunitionService} from "../shared/ammunition.service";
import {NgForm} from "@angular/forms";
import {Location} from "@angular/common";
import {Ammunition} from "../shared/ammunition.model";

@Component({
  selector: 'app-ammo-new',
  templateUrl: './ammo-new.component.html',
  styleUrls: ['./ammo-new.component.css']
})
export class AmmoNewComponent implements OnInit {

  constructor(private ammunitionService: AmmunitionService,
              private location: Location) { }

  ngOnInit(): void {
  }

  @ViewChild('f') form: NgForm;

  onClear() {
    this.form.reset();
  }

  onSubmit(form: NgForm) {
    if(form.value.noOfRounds && form.value.price && form.value.price>=0
      && form.value.noOfRounds>=0 && form.value.caliber) {

      const ammo:Ammunition = <Ammunition>{
        noOfRounds: form.value.noOfRounds,
        caliber: form.value.caliber,
        manufacturer: form.value.manufacturer,
        price: form.value.price};

      this.ammunitionService.saveAmmo(ammo).subscribe(ammo => {
        console.log("saved ammo: ", ammo);
        this.location.back();
      });
    }
    else alert("Invalid ammunition data!");
  }

}
