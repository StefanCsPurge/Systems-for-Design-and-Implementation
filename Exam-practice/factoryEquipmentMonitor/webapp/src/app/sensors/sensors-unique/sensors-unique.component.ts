import { Component, OnInit } from '@angular/core';
import {SensorService} from "../shared/sensor.service";
import {Sensor} from "../shared/sensor.model";
import {HashTable} from "../shared/hashtable.model";
import { interval, Subscription } from 'rxjs';

@Component({
  selector: 'app-sensors-unique',
  templateUrl: './sensors-unique.component.html',
  styleUrls: ['./sensors-unique.component.css']
})
export class SensorsUniqueComponent implements OnInit {

  names: string[];
  sensorsTable: HashTable<Sensor[]> = {};
  showEnabled = false;
  subscription: Subscription;
  constructor(private sensorService: SensorService) { }

  ngOnInit(): void {
    const source = interval(1000);
    this.subscription = source.subscribe(_ =>
      this.sensorService.getSensorNames()
      .subscribe(names => {this.names = names; if(this.showEnabled) this.showLastMeasurements()})
    );
  }

  showLastMeasurements() {
    this.showEnabled = true;

    for(let name of this.names){
      this.sensorService.getSensorsByName(name).subscribe(s => {
        this.sensorsTable[name] = s;
      });
    }
  }

  stopSensor(name: string) {
     this.sensorService.stopSensor(name).subscribe();
  }
}
