package model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Sensor {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Long measurement;

    private Long time; // time in milliseconds

}
