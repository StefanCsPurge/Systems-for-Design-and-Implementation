package ro.ubb.gunstore.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AmmoDto extends BaseDto{
    private int noOfRounds;
    private float caliber;
    private String manufacturer;
    private float price;
}
