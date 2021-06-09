import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SensorsComponent } from './sensors/sensors.component';
import {SensorService} from "./sensors/shared/sensor.service";
import {HttpClientModule} from "@angular/common/http";
import { SensorsUniqueComponent } from './sensors/sensors-unique/sensors-unique.component';

@NgModule({
  declarations: [
    AppComponent,
    SensorsComponent,
    SensorsUniqueComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
  ],
  providers: [SensorService,],
  bootstrap: [AppComponent]
})
export class AppModule { }
