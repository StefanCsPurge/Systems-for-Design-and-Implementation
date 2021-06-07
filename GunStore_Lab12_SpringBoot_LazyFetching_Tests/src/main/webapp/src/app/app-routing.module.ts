import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ClientsComponent} from "./clients/clients.component";
import {GunsComponent} from "./guns/guns.component";
import {GunNewComponent} from "./guns/gun-new/gun-new.component";
import {ClientDetailComponent} from "./clients/client-detail/client-detail.component";
import {ClientNewComponent} from "./clients/client-new/client-new.component";
import {ClientDeleteComponent} from "./clients/client-delete/client-delete.component";
import {AccessoriesComponent} from "./accessories/accessories.component";
import {AmmunitionComponent} from "./ammunition/ammunition.component";
import {AccessoryNewComponent} from "./accessories/accessory-new/accessory-new.component";
import {AmmoNewComponent} from "./ammunition/ammo-new/ammo-new.component";
import {AccessoryEditComponent} from "./accessories/accessory-edit/accessory-edit.component";
import {OrdersComponent} from "./orders/orders.component";
import {ClientOrderNewComponent} from "./orders/client-order-new/client-order-new.component";
import {GunsTop3Component} from "./guns/guns-top3/guns-top3.component";


const routes: Routes = [
  // { path: '', redirectTo: '/home', pathMatch: 'full' },
  {path: 'clients', component: ClientsComponent},
  {path: 'client/detail/:id', component: ClientDetailComponent},
  {path: 'client/deleteConfirm/:id', component: ClientDeleteComponent},
  {path: 'client-new', component: ClientNewComponent},

  {path: 'client/orders/:id', component: OrdersComponent},
  {path: 'client-order-new', component: ClientOrderNewComponent},

  {path: 'guns', component: GunsComponent},
  {path: 'gun-new', component: GunNewComponent},
  {path: 'guns-top3', component: GunsTop3Component},

  {path: 'accessories', component: AccessoriesComponent},
  {path: 'accessory-new', component: AccessoryNewComponent},
  {path: 'accessory-edit/:id', component: AccessoryEditComponent},

  {path: 'ammunition', component: AmmunitionComponent},
  {path: 'ammo-new', component: AmmoNewComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
