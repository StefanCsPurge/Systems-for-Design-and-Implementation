import {Injectable} from '@angular/core';

import {HttpClient} from "@angular/common/http";

import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {Gun} from "./gun.model";


@Injectable()
export class GunService {
  private gunsUrl = 'http://localhost:8080/api/guns';

  constructor(private httpClient: HttpClient) {
  }

  getGuns(): Observable<Gun[]> {
    return this.httpClient
      .get<Array<Gun>>(this.gunsUrl);
  }

  getGun(id: number): Observable<Gun> {
    return this.getGuns()
      .pipe(
        map(guns => guns.find(gun => gun.id === id))
      );
  }

  saveGun(gun: Gun): Observable<Gun>{
    return this.httpClient.post<Gun>(this.gunsUrl, gun);
  }

  update(gun): Observable<Gun> {
    const url = `${this.gunsUrl}/${gun.id}`;
    return this.httpClient
      .put<Gun>(url, gun);
  }

  deleteGun(id: number): Observable<Response>{
    const url = `${this.gunsUrl}/${id}`;
    return this.httpClient.delete<Response>(url);
  }

}
