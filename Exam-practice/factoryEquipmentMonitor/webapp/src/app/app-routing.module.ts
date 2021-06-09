import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {SensorsComponent} from "./sensors/sensors.component";

const routes: Routes = [
  {path: '', redirectTo: '/all', pathMatch: 'full' },
  {path: 'all', component: SensorsComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
