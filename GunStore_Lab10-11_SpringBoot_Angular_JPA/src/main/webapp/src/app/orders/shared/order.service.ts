import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Order} from "./order.model";

@Injectable({
  providedIn: 'root',
})
export class OrderService {
  private ordersUrl = 'http://localhost:8080/api/storeOrders';
  public static clientId: number;

  constructor(private httpClient: HttpClient) { }

  getClientOrders(clientId: number): Observable<Order[]> {
    const url = `${this.ordersUrl}/${clientId}`;
    return this.httpClient.get<Array<Order>>(url);
  }

  saveOrder(order: Order): Observable<Order> {
    return this.httpClient.post<Order>(this.ordersUrl, order);
  }

  updateOrder(order: Order): Observable<Order> {
    return this.httpClient.put<Order>(this.ordersUrl, order);
  }

  deleteClientOrder(clientId, orderId): Observable<Response> {
    const url = `${this.ordersUrl}/${clientId}-${orderId}`;
    return this.httpClient.delete<Response>(url);
  }
}
