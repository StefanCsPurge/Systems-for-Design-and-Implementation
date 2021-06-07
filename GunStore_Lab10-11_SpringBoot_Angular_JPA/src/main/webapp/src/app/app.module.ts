import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {AppComponent} from './app.component';
import {AppRoutingModule} from "./app-routing.module";
import {ClientDetailComponent} from "./clients/client-detail/client-detail.component";
import {ClientsComponent} from "./clients/clients.component";
import {ClientsListComponent} from "./clients/clients-list/clients-list.component";
import {ClientService} from "./clients/shared/client.service";
import { GunsComponent } from './guns/guns.component';
import { GunsListComponent } from './guns/guns-list/guns-list.component';
import {GunService} from "./guns/shared/gun.service";
import { GunNewComponent } from './guns/gun-new/gun-new.component';
import { ClientNewComponent } from './clients/client-new/client-new.component';
import { ClientDeleteComponent } from './clients/client-delete/client-delete.component';
import { AccessoriesComponent } from './accessories/accessories.component';
import { AmmunitionComponent } from './ammunition/ammunition.component';
import { AccessoriesListComponent } from './accessories/accessories-list/accessories-list.component';
import { AccessoryNewComponent } from './accessories/accessory-new/accessory-new.component';
import { AmmunitionListComponent } from './ammunition/ammunition-list/ammunition-list.component';
import { AmmoNewComponent } from './ammunition/ammo-new/ammo-new.component';
import {AccessoryService} from "./accessories/shared/accessory.service";
import {AmmunitionService} from "./ammunition/shared/ammunition.service";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MatSliderModule } from '@angular/material/slider';
import { MatTableModule } from '@angular/material/table';
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatSortModule} from "@angular/material/sort";
import { AccessoryEditComponent } from './accessories/accessory-edit/accessory-edit.component';
import { OrdersComponent } from './orders/orders.component';
import {OrderService} from "./orders/shared/order.service";
import { ClientOrdersListComponent } from './orders/client-orders-list/client-orders-list.component';
import { ClientOrderNewComponent } from './orders/client-order-new/client-order-new.component';
import { GunsTop3Component } from './guns/guns-top3/guns-top3.component';

@NgModule({
  declarations: [
    AppComponent,
    ClientDetailComponent,
    ClientsComponent,
    ClientsListComponent,
    GunsComponent,
    GunsListComponent,
    GunNewComponent,
    ClientNewComponent,
    ClientDeleteComponent,
    AccessoriesComponent,
    AmmunitionComponent,
    AccessoriesListComponent,
    AccessoryNewComponent,
    AmmunitionListComponent,
    AmmoNewComponent,
    AccessoryEditComponent,
    OrdersComponent,
    ClientOrdersListComponent,
    ClientOrderNewComponent,
    GunsTop3Component,

  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatSliderModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule
  ],
  providers: [ClientService, GunService, AccessoryService, AmmunitionService, OrderService], // here add the other services
  bootstrap: [AppComponent]
})
export class AppModule {
}
