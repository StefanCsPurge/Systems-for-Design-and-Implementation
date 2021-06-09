package adaptor;

import model.Sensor;

public class SensorAdaptor {
    public static String sensorDataToMessage(String name, Integer id, Integer measurement){
        return name + "," + id + "," +  measurement;
    }

    public static Sensor messageToSensor(String message) {
        String[] tokens = message.split(",");
        return new Sensor(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
    }
}
