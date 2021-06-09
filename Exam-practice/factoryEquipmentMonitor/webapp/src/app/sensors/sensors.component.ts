import { Component, OnInit } from '@angular/core';
import {Sensor} from "./shared/sensor.model";
import {SensorService} from "./shared/sensor.service";

@Component({
  selector: 'app-sensors',
  templateUrl: './sensors.component.html',
  styleUrls: ['./sensors.component.css']
})
export class SensorsComponent implements OnInit {
  sensors: Sensor[];

  constructor(private sensorService: SensorService) {
  }

  ngOnInit(): void {
    this.sensorService.getAllSensors()
      .subscribe(sensors => {this.sensors = sensors;});
  }

}
