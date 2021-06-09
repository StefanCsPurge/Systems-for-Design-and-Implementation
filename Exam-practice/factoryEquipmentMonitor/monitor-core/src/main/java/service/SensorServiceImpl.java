package service;

import model.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.SensorRepository;

import java.util.List;

@Service
public class SensorServiceImpl implements SensorService{
    @Autowired
    private SensorRepository sensorRepository;

    @Override
    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }

    @Override
    public List<Sensor> getMostRecent4(String name) {
        return sensorRepository.findTop4ByNameOrderByTimeDesc(name);
    }

    @Override
    public List<String> getSensorNames() {
        return sensorRepository.getSensorNames();
    }
}
