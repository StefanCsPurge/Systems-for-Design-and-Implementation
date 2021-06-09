import { Component, OnInit } from '@angular/core';
import {SensorService} from "../shared/sensor.service";
import {Sensor} from "../shared/sensor.model";
import {HashTable} from "../shared/hashtable.model";

@Component({
  selector: 'app-sensors-unique',
  templateUrl: './sensors-unique.component.html',
  styleUrls: ['./sensors-unique.component.css']
})
export class SensorsUniqueComponent implements OnInit {

  names: string[];
  sensorsTable: HashTable<Sensor[]> = {};
  showEnabled = false;
  constructor(private sensorService: SensorService) { }

  ngOnInit(): void {
    this.sensorService.getSensorNames()
      .subscribe(names => {this.names = names;});
  }

  showLastMeasurements() {
    this.showEnabled = true;

    for(let name of this.names){
      this.sensorService.getSensorsByName(name).subscribe(s => {
        this.sensorsTable[name] = s;
      });
    }
  }
}
