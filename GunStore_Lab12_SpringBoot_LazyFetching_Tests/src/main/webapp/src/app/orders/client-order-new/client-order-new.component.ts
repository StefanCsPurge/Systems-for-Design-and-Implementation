import { Component, OnInit } from '@angular/core';
import {OrderService} from "../shared/order.service";
import {Gun} from "../../guns/shared/gun.model";
import {GunService} from "../../guns/shared/gun.service";
import {Location} from "@angular/common";
import {Order} from "../shared/order.model";

@Component({
  selector: 'app-client-order-new',
  templateUrl: './client-order-new.component.html',
  styleUrls: ['./client-order-new.component.css']
})
export class ClientOrderNewComponent implements OnInit {

  guns: Gun[];
  selectedGun: Gun;
  clientId: number;
  constructor(private orderService: OrderService,
              private gunService: GunService,
              private location: Location) { }

  ngOnInit(): void {
    this.clientId = OrderService.clientId;
    this.gunService.getGuns()
      .subscribe(guns => this.guns = guns);
  }

  addOrder(): void {
    if(!this.selectedGun)
      alert("Please select a gun");
    else{
      const order:Order = <Order>{clientId: this.clientId, gunId: this.selectedGun.id};
      this.orderService.saveOrder(order).subscribe(order => {
        console.log("save result: ", order);
        this.location.back();
      });
    }
  }
}
