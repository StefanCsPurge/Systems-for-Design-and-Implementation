package ro.ubb.gunstore.web.dto;

// data transfer object - useful if you want to transmit only some of the data instead of all
// in other words the DTO contains only the data that we want to show to the screen (in the context of web interfaces)
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GunDto extends BaseDto{
    private String model;
    private String manufacturer;
    private String type;
    private float weight;
    private float price;
    private Long timesSold;
}
