package TCP;

import model.Sensor;
import message.Message;
import adaptor.SensorAdaptor;

import java.util.Date;
import java.sql.DriverManager;

public class MessageHandler {
    public MessageHandler(){
    }

    /**
     * Takes the request message from client and tries to add
     * the new sensor data to the DB
     * @param request Message from the sensor
     * @return Message from the server as a reply
     */
    public Message addSensorData(Message request){
        Sensor receivedSensor = SensorAdaptor.messageToSensor(request.getBody());
        try {
            String stmt = "insert into sensor (name, measurement, time) values (?, ?, ?)";
            try (var connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/sensors", "postgres", "1234");
                 var ps = connection.prepareStatement(stmt)) {
                ps.setString(1, receivedSensor.getSensorName());
                ps.setLong(2, receivedSensor.getMeasurement());
                ps.setLong(3, new Date().getTime());
                ps.executeUpdate();
            }
            return new Message("ok", "true");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return new Message("error", "false");
        }
    }

    public Message stopSensor(Message request) {
        String sensorName = request.getBody();
        System.out.println("Got sensor to stop: " + sensorName);
        return new Message("ok", "true");
    }
}
