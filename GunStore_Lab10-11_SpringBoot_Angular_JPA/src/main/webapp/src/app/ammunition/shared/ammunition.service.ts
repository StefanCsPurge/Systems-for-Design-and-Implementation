import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Ammunition} from "./ammunition.model";

@Injectable()
export class AmmunitionService {

  private ammoUrl = 'http://localhost:8080/api/ammunition';
  constructor(private httpClient: HttpClient) { }

  getAmmunition(): Observable<Ammunition[]> {
    return this.httpClient
      .get<Array<Ammunition>>(this.ammoUrl);
  }

  saveAmmo(ammo: Ammunition): Observable<Ammunition>{
    return this.httpClient.post<Ammunition>(this.ammoUrl, ammo);
  }

  deleteAmmo(id: number): Observable<Response>{
    const url = `${this.ammoUrl}/${id}`;
    return this.httpClient.delete<Response>(url);
  }
}
