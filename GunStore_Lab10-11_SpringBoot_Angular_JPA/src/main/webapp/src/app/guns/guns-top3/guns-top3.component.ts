import { Component, OnInit } from '@angular/core';
import {Gun} from "../shared/gun.model";
import {GunService} from "../shared/gun.service";
import {Location} from '@angular/common';

@Component({
  selector: 'app-guns-top3',
  templateUrl: './guns-top3.component.html',
  styleUrls: ['./guns-top3.component.css']
})
export class GunsTop3Component implements OnInit {

  guns: Gun[];
  constructor(private gunService: GunService,
              private location: Location) { }

  ngOnInit(): void {
    this.gunService.getTop3Guns()
      .subscribe(guns => this.guns = guns);
  }

  goBack(): void {
    this.location.back();
  }

}
