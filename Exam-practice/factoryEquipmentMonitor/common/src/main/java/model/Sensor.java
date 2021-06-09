package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Sensor {
    String sensorName;
    Integer sensorId;
    Integer measurement;
}
