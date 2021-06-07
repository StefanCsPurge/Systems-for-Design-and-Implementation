import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Params, Router} from "@angular/router";
import {Location} from "@angular/common";
import {switchMap} from "rxjs/operators";
import {OrderService} from "./shared/order.service";
import {ClientService} from "../clients/shared/client.service";

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {
  @Input() clientId: number;

  constructor(private orderService: OrderService,
              private clientService: ClientService,
              private route: ActivatedRoute,
              private router: Router,
              private location: Location) {}

  ngOnInit(): void {
    this.route.params.pipe(
      switchMap((params: Params) =>
        this.clientService.getClient(+params['id'])))
      .subscribe(client => {
        this.clientId = client.id;
        OrderService.clientId = this.clientId;
      });

  }

  goBack(): void {
    this.location.back();
  }

  gotoAddOrder() {
    this.router.navigate(['/client-order-new']);
  }
}
