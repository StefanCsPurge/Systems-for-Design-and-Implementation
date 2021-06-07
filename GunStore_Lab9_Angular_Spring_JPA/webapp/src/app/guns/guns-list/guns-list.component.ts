import { Component, OnInit } from '@angular/core';
import {Gun} from "../shared/gun.model";
import {GunService} from "../shared/gun.service";

@Component({
  selector: 'app-guns-list',
  templateUrl: './guns-list.component.html',
  styleUrls: ['./guns-list.component.css']
})
export class GunsListComponent implements OnInit {
  guns: Gun[];

  constructor(private gunService: GunService) { }

  ngOnInit(): void {
    this.gunService.getGuns()
      .subscribe(guns => this.guns = guns); // non-blocking (observable)
  }

  deleteSelectedGuns() {
    if (confirm("Are you sure you want to delete the selected guns?")) {
      let selectedGuns = this.guns
        .filter(opt => opt.checked)
        .map(opt => opt.id);

      for (let id of selectedGuns) {
        this.gunService.deleteGun(id).subscribe(_ => this.ngOnInit());
      }
    }
  }
}
