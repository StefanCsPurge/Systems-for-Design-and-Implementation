import { Component, OnInit } from '@angular/core';
import {ClientService} from "../shared/client.service";
import {Client} from "../shared/client.model";
import {Location} from "@angular/common";

@Component({
  selector: 'app-client-new',
  templateUrl: './client-new.component.html',
  styleUrls: ['./client-new.component.css']
})
export class ClientNewComponent implements OnInit {

  constructor(private clientService: ClientService,
              private location: Location) { }

  ngOnInit(): void {
  }

  saveClient(name: string, cnp: string, budget: string, city: string, street: string, number: string) {
    const client:Client = <Client>{name, cnp, budget: +budget, city, street, streetNumber: +number};
    this.clientService.saveClient(client).subscribe(client => {
      console.log("saved client: ", client);
      this.goBack()
    });
  }

  goBack(): void {
    this.location.back();
  }

}
