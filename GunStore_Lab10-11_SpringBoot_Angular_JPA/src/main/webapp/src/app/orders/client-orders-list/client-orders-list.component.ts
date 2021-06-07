import { Component, OnInit } from '@angular/core';
import {OrderService} from "../shared/order.service";
import {Order} from "../shared/order.model";

@Component({
  selector: 'app-client-orders-list',
  templateUrl: './client-orders-list.component.html',
  styleUrls: ['./client-orders-list.component.css']
})
export class ClientOrdersListComponent implements OnInit {
  orders: Order[];

  constructor(private orderService: OrderService) { }

  ngOnInit(): void {
    this.orderService.getClientOrders(OrderService.clientId)
      .subscribe(orders => this.orders = orders);  // get all client orders
  }

  deleteClientOrder(clientId: number, orderId: number) {
    this.orderService.deleteClientOrder(clientId,orderId).subscribe(response =>
    {
      console.log(response);
      this.ngOnInit();
    });
  }
}
