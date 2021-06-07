import { Component, OnInit } from '@angular/core';
import {ClientService} from "../../clients/shared/client.service";

@Component({
  selector: 'app-guns-delete-conf',
  templateUrl: './guns-delete-conf.component.html',
  styleUrls: ['./guns-delete-conf.component.css']
})
export class GunsDeleteConfComponent implements OnInit {

  constructor(private Service: ClientService) { }

  ngOnInit(): void {
  }

}
