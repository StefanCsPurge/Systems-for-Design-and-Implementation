package service;

import model.Sensor;

import java.util.List;

public interface SensorService {
    List<Sensor> getAllSensors();
    List<Sensor> getMostRecent4(String name);
    List<String> getSensorNames();
}
