package dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SensorDto {
    private Long id;
    private String name;
    private Long measurement;
    private Long time;
}