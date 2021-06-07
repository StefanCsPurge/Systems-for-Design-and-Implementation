import { Component, OnInit } from '@angular/core';
import {GunService} from "../shared/gun.service";
import {Gun} from "../shared/gun.model";
import {Location} from "@angular/common";

@Component({
  selector: 'app-gun-new',
  templateUrl: './gun-new.component.html',
  styleUrls: ['./gun-new.component.css']
})
export class GunNewComponent implements OnInit {

    constructor(private gunService: GunService,
                private location: Location) { }

    ngOnInit(): void {
    }

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
}
