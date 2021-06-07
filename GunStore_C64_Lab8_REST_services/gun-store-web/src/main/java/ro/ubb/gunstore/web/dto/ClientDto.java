package ro.ubb.gunstore.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ClientDto extends BaseDto{
    private String name;
    private String cnp;
    private float budget;
}