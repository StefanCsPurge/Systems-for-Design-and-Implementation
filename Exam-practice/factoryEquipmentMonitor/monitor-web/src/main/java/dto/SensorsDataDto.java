package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SensorsDataDto {
    private List<SensorDto> sensorsData;

    @Override
    public String toString() {
        return "SensorsDataDto{" +
                sensorsData.stream().map(e -> "\n\t" + e.toString()).reduce("", String::concat)
                + " }";
    }
}
