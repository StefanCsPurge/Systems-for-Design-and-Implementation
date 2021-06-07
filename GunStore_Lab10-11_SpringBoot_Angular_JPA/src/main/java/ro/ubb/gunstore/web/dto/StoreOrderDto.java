package ro.ubb.gunstore.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

public class StoreOrderDto extends BaseDto {
    private Long gunId;
    private Long clientId;
}
