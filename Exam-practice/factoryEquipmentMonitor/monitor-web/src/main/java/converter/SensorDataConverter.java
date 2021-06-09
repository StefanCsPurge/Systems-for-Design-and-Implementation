package converter;

import dto.SensorDto;
import org.springframework.stereotype.Component;
import model.Sensor;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SensorDataConverter implements Converter<Sensor, SensorDto>{
    @Override
    public Sensor convertDtoToModel(SensorDto dto) {
        var sensorData = new Sensor();
        sensorData.setId(dto.getId());
        sensorData.setMeasurement(dto.getMeasurement());
        sensorData.setTime(dto.getTime());
        sensorData.setName(dto.getName());
        return sensorData;
    }

    @Override
    public SensorDto convertModelToDto(Sensor sensorData) {
        SensorDto dto = new SensorDto();
        dto.setId(sensorData.getId());
        dto.setMeasurement(sensorData.getMeasurement());
        dto.setTime(sensorData.getTime());
        dto.setName(sensorData.getName());
        return dto;
    }

    public List<Long> convertModelsToIDs(List<Sensor> models) {
        return models.stream()
                .map(Sensor::getId)
                .collect(Collectors.toList());
    }

    public List<Long> convertDTOsToIDs(List<SensorDto> dtos) {
        return dtos.stream()
                .map(SensorDto::getId)
                .collect(Collectors.toList());
    }

    public List<SensorDto> convertModelsToDtos(Collection<Sensor> models) {
        return models.stream()
                .map(this::convertModelToDto)
                .collect(Collectors.toList());
    }
}
