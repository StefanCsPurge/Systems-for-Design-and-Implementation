import {Component, Input, OnInit} from '@angular/core';
import {Accessory} from "../shared/accessory.model";
import {AccessoryService} from "../shared/accessory.service";
import {ActivatedRoute, Params} from "@angular/router";
import {Location} from "@angular/common";
import {switchMap} from "rxjs/operators";
import {Gun} from "../../guns/shared/gun.model";
import {GunService} from "../../guns/shared/gun.service";

@Component({
  selector: 'app-accessory-edit',
  templateUrl: './accessory-edit.component.html',
  styleUrls: ['./accessory-edit.component.css']
})
export class AccessoryEditComponent implements OnInit {

  @Input() accessory: Accessory;
  guns: Gun[];
  selectedGun: Gun;

  constructor(private accessoryService: AccessoryService,
              private gunService: GunService,
              private route: ActivatedRoute,
              private location: Location) {}

  ngOnInit(): void {
    this.route.params
      .pipe(switchMap((params: Params) => this.accessoryService.getAccessory(+params['id'])))
      .subscribe(acc => this.accessory = acc);
    this.gunService.getGuns()
      .subscribe(guns => this.guns = guns);
  }

  update(): void{
    this.accessoryService.updateAccessory(this.accessory)
      .subscribe(_ => this.goBack());
  }

  goBack() {
    this.location.back();
  }
}
