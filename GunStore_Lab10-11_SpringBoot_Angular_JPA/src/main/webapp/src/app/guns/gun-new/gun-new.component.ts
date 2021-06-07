import {Component, OnInit, OnDestroy, ViewChild} from '@angular/core';
import {GunService} from "../shared/gun.service";
import {Gun} from "../shared/gun.model";
import {Location} from "@angular/common";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-gun-new',
  templateUrl: './gun-new.component.html',
  styleUrls: ['./gun-new.component.css']
})
export class GunNewComponent implements OnInit, OnDestroy {

    constructor(private gunService: GunService,
                private location: Location) { }

    ngOnInit(): void {
    }

    @ViewChild('f') form: NgForm;

    saveGun(model: string, manufacturer: string, type: string, weight: string, price: string) {
        //const gun:Gun = {id:0, model, manufacturer, type, weight: +weight, price: +price};
        const gun:Gun = <Gun>{model, manufacturer, type, weight: +weight, price: +price, timesSold: 0};
        this.gunService.saveGun(gun).subscribe(gun => {
          console.log("saved gun: ", gun);
          this.goBack()
        });
    }

    goBack(): void {
      this.location.back();
    }

    onSubmit(form: NgForm) {
        if(form.value.model && form.value.price && form.value.price>=0
           && form.value.weight && form.value.weight>=0) {

          const gun:Gun = <Gun>{model: form.value.model,
                                manufacturer: form.value.manufacturer,
                                type: form.value.type,
                                weight: form.value.weight,
                                price: form.value.price,
                                timesSold: 0};

          this.gunService.saveGun(gun).subscribe(gun => {
            console.log("saved gun: ", gun);
            this.goBack()
          });
        }
        else alert("Invalid gun data!");
    }

    onClear() {
      this.form.reset();
    }

    ngOnDestroy(): void {
    }
}
