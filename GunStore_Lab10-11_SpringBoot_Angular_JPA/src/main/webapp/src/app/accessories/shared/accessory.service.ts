import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Accessory} from "./accessory.model";
import {map} from "rxjs/operators";

@Injectable()
export class AccessoryService {
  private accessoriesUrl = 'http://localhost:8080/api/accessories';
  constructor(private httpClient: HttpClient) { }

  getAccessories(): Observable<Accessory[]> {
    return this.httpClient
      .get<Array<Accessory>>(this.accessoriesUrl);
  }

  saveAccessory(acc: Accessory): Observable<Accessory>{
    return this.httpClient.post<Accessory>(this.accessoriesUrl, acc);
  }

  deleteAccessory(id: number): Observable<Response>{
    const url = `${this.accessoriesUrl}/${id}`;
    return this.httpClient.delete<Response>(url);
  }

  updateAccessory(updatedAcc: Accessory): Observable<Accessory> {
    const url = `${this.accessoriesUrl}/${updatedAcc.id}`;
    return this.httpClient
      .put<Accessory>(url, updatedAcc);
  }

  getAccessory(id: number) {
    return this.getAccessories()
      .pipe(
        map(accessories => accessories.find(acc => acc.id === id))
      );
  }
}
