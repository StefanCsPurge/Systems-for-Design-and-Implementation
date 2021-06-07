import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';

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
import { GunsDeleteConfComponent } from './guns/guns-delete-conf/guns-delete-conf.component';


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
    GunsDeleteConfComponent,


  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
  ],
  providers: [ClientService, GunService],  // here add the other services
  bootstrap: [AppComponent]
})
export class AppModule {
}
