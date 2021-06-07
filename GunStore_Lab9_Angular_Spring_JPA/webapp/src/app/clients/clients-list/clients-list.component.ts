import {Component, OnInit} from '@angular/core';
import {Client} from "../shared/client.model";
import {ClientService} from "../shared/client.service";
import {Router} from "@angular/router";
import {BehaviorSubject} from "rxjs";


@Component({
  moduleId: module.id,
  selector: 'app-clients-list',
  templateUrl: './clients-list.component.html',
  styleUrls: ['./clients-list.component.css'],
})
export class ClientsListComponent implements OnInit {
  errorMessage: string;
  clients: Array<Client>;
  selectedClient: Client;
  isLoading$: BehaviorSubject<boolean> = new BehaviorSubject(false);

  constructor(private clientService: ClientService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getClients();
  }

  getClients() {
    this.isLoading$.next(true);
    this.clientService.getClients()
      .subscribe(
        data => {
          this.clients = data;
          this.isLoading$.next(false);
        },
        error => {
          this.errorMessage = <any>error
        }
      );
  }

  onSelect(client: Client): void {
      this.selectedClient = client;
  }

  gotoDetail(): void {
    this.router.navigate(['/client/detail', this.selectedClient.id]);
  }

  gotoDelete(): void {
    this.router.navigate(['/client/deleteConfirm', this.selectedClient.id]);
  }
}
