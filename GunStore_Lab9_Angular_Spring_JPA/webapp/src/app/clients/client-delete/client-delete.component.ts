import {Component, Input, OnInit} from '@angular/core';
import {ClientService} from "../shared/client.service";
import {ActivatedRoute, Params} from "@angular/router";
import {Location} from "@angular/common";
import {Client} from "../shared/client.model";
import {switchMap} from "rxjs/operators";

@Component({
  selector: 'app-client-delete',
  templateUrl: './client-delete.component.html',
  styleUrls: ['./client-delete.component.css']
})
export class ClientDeleteComponent implements OnInit {

  @Input() client: Client;

  constructor(private clientService: ClientService,
              private route: ActivatedRoute,
              private location: Location) { }

  ngOnInit(): void {
    this.route.params
      .pipe(switchMap((params: Params) => this.clientService.getClient(+params['id'])))
      .subscribe(client => this.client = client);
  }

  goBack(): void {
    this.location.back();
  }

  delete(): void {
    this.clientService.deleteClient(this.client.id)
      .subscribe(_ => this.goBack());
  }
}
