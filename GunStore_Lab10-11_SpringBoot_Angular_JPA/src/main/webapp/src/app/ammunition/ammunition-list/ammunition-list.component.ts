import { Component, OnInit } from '@angular/core';
import {Ammunition} from "../shared/ammunition.model";
import {AmmunitionService} from "../shared/ammunition.service";

@Component({
  selector: 'app-ammunition-list',
  templateUrl: './ammunition-list.component.html',
  styleUrls: ['./ammunition-list.component.css']
})
export class AmmunitionListComponent implements OnInit {
  ammunition: Ammunition[];

  constructor(private ammunitionService: AmmunitionService) { }

  ngOnInit(): void {
    this.ammunitionService.getAmmunition()
      .subscribe(ammunition => this.ammunition = ammunition);
  }

  filterAmmoByCaliber(cal: number) {
    // client side filter
    if (!cal)
      this.ngOnInit();
    else
      this.ammunitionService.getAmmunition()
        .subscribe(ammo => this.ammunition = ammo.filter(ammo => ammo.caliber == cal));
  }

  deleteAmmo(id: number) {
    if (confirm("Are you sure you want to delete the ammo with ID " + id + " ?"))
      this.ammunitionService.deleteAmmo(id).subscribe(_ => this.ngOnInit());
  }

  sortAmmoByPrice() {
    this.ammunition.sort((a,b) => (a.price < b.price ? -1 : 1));
  }
}
