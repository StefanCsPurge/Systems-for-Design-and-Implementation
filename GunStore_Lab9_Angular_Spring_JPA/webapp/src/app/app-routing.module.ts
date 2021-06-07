import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ClientsComponent} from "./clients/clients.component";
import {GunsComponent} from "./guns/guns.component";
import {GunNewComponent} from "./guns/gun-new/gun-new.component";
import {ClientDetailComponent} from "./clients/client-detail/client-detail.component";
import {ClientNewComponent} from "./clients/client-new/client-new.component";
import {ClientDeleteComponent} from "./clients/client-delete/client-delete.component";


const routes: Routes = [
  // { path: '', redirectTo: '/home', pathMatch: 'full' },
  {path: 'clients', component: ClientsComponent},
  {path: 'client/detail/:id', component: ClientDetailComponent},
  {path: 'client/deleteConfirm/:id', component: ClientDeleteComponent},
  {path: 'client-new', component: ClientNewComponent},

  {path: 'guns', component: GunsComponent},
  {path: 'gun-new', component: GunNewComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
