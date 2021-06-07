package ro.ubb.gunstore.web.dto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AccessoryDto extends BaseDto {
    private String name;
    private String type;
    private String company;
    private float price;
    private Long gunId;
}
