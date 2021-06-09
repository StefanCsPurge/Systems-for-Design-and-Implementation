import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Sensor} from "./sensor.model";

@Injectable()
export class SensorService {
  private moviesUrl = 'http://localhost:8080/api/sensors';
  constructor(private httpClient: HttpClient) { }

  getAllSensors() {
    return this.httpClient
      .get<Array<Sensor>>(this.moviesUrl);
  }
}
