import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {SensorsComponent} from "./sensors/sensors.component";
import {SensorsUniqueComponent} from "./sensors/sensors-unique/sensors-unique.component";

const routes: Routes = [
  {path: '', redirectTo: '/all', pathMatch: 'full' },
  {path: 'all', component: SensorsComponent},
  {path: 'sensors', component: SensorsUniqueComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
