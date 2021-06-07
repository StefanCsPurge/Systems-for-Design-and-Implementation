import {Injectable} from '@angular/core';

import {HttpClient} from "@angular/common/http";

import {Client} from "./client.model";

import {Observable} from "rxjs";
import {map} from "rxjs/operators";


@Injectable()
export class ClientService {
  private clientsUrl = 'http://localhost:8080/api/clients';

  constructor(private httpClient: HttpClient) {
  }

  getClients(): Observable<Client[]> {
    return this.httpClient
      .get<Array<Client>>(this.clientsUrl);
  }

  getClient(id: number): Observable<Client> {
    return this.getClients()
      .pipe(
        map(clients => clients.find(client => client.id === id))
      );
  }

  saveClient(client: Client): Observable<Client>{
    return this.httpClient.post<Client>(this.clientsUrl, client);
  }

  updateClient(client): Observable<Client> {
    const url = `${this.clientsUrl}/${client.id}`;
    return this.httpClient
      .put<Client>(url, client);
  }

  deleteClient(id: number): Observable<Response>{
    const url = `${this.clientsUrl}/${id}`;
    return this.httpClient.delete<Response>(url);
  }

}
