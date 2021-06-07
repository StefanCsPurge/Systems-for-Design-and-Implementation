import {Component, OnInit, ViewChild} from '@angular/core';
import {Accessory} from "../shared/accessory.model";
import {AccessoryService} from "../shared/accessory.service";
import {MatSort} from "@angular/material/sort";
import {Router} from "@angular/router";

@Component({
  selector: 'app-accessories-list',
  templateUrl: './accessories-list.component.html',
  styleUrls: ['./accessories-list.component.css']
})
export class AccessoriesListComponent implements OnInit {
  accessories: Accessory[];
  displayedColumns = ['id','name', 'type', 'company', 'price', 'gunId', 'options'];
  sortDir = 1;

  @ViewChild(MatSort) sort: MatSort;

  constructor(private accessoriesService: AccessoryService,
              private router: Router) { }

  ngOnInit(): void {
    this.accessoriesService.getAccessories()
      .subscribe(accessories => this.accessories = accessories);
  }

  filterAccessoriesByType(type: string) {
    // client side filter
    if (type.length == 0 || type == "*")
      this.ngOnInit();
    else
      this.accessoriesService.getAccessories()
        .subscribe(accessories => this.accessories = accessories.filter(acc => acc.type.toLowerCase() == type.toLowerCase()));
  }

  deleteAccessory(id: number) {
    if (confirm("Are you sure you want to delete the accessory with ID " + id + " ?"))
      this.accessoriesService.deleteAccessory(id).subscribe(_ => this.ngOnInit());
  }

  sortByPrice(table) {
    this.accessories.sort((a,b) => (a.price < b.price ? -this.sortDir : this.sortDir));
    table.renderRows();
    this.sortDir *= -1;
  }

  gotoEdit(accId: number) {
    this.router.navigate(['/accessory-edit', accId]);
  }
}
