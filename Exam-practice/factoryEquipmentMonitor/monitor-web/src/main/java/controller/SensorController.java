package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.SensorService;
import converter.SensorDataConverter;
import dto.SensorsDataDto;

import java.util.List;


@RestController
public class SensorController {
    @Autowired
    private SensorService sensorDataService;

    @Autowired
    private SensorDataConverter sensorDataConverter;


    @RequestMapping(value = "/sensors")
     SensorsDataDto getAllSensorData(){
        return new SensorsDataDto(sensorDataConverter.convertModelsToDtos(sensorDataService.getAllSensors()));
    }

    @RequestMapping(value = "/sensors/getMostRecent4/{name}", method = RequestMethod.GET)
    SensorsDataDto getMostRecent4(@PathVariable String name){
        return new SensorsDataDto(sensorDataConverter.convertModelsToDtos(sensorDataService.getMostRecent4(name)));
    }

    @RequestMapping(value = "/sensors/names")
    List<String> getSensorNames(){
        return sensorDataService.getSensorNames();
    }

}
