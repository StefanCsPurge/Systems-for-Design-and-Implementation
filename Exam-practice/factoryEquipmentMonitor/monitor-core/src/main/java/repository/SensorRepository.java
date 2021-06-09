package repository;

import model.Sensor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SensorRepository extends Repository<Long, Sensor> {
    List<Sensor> findTop4ByNameOrderByTimeDesc(String name);

    @Query(value = "select distinct name from sensor", nativeQuery = true)
    List<String> getSensorNames();
}
