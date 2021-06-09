package controller;

import dto.SensorDto;
import model.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.SensorService;
import converter.SensorDataConverter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;


@RestController
public class SensorController {
    @Autowired
    private SensorService sensorDataService;

    @Autowired
    private SensorDataConverter sensorDataConverter;


    @RequestMapping(value = "/sensors")
    List<SensorDto> getAllSensorData(){
        return sensorDataConverter.convertModelsToDtos(sensorDataService.getAllSensors());
    }

    @RequestMapping(value = "/sensors/getMostRecent4/{name}", method = RequestMethod.GET)
    List<SensorDto> getMostRecent4(@PathVariable String name){
        return sensorDataConverter.convertModelsToDtos(sensorDataService.getMostRecent4(name));
    }

    @RequestMapping(value = "/sensors/names")
    List<String> getSensorNames(){
        return sensorDataService.getSensorNames();
    }

    @RequestMapping(value = "/sensors/stopSensor/{name}", method = RequestMethod.GET)
    void stopSensor(@PathVariable String name){
        Message message = new Message("stopSensor", name);
        try (Socket socket = new Socket(Message.HOST, Message.PORT)) {
            try (InputStream is = socket.getInputStream()) {
                try (OutputStream os = socket.getOutputStream()) {
                    //System.out.println("sendAndReceive - sending request: " + request);
                    message.writeTo(os);
                    //System.out.println("sendAndReceive - received response: ");
                    Message response = new Message();
                    response.readFrom(is);
                    System.out.println(response);
                }
            }
        } catch (IOException e) {
            System.out.println("error when connecting to server " + e.getMessage());
        }
    }

}
