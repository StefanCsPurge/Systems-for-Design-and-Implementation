package ro.ubb.gunstore.web.dto;

import lombok.*;

import java.io.Serializable;

/**
 * Created by C64.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BaseDto implements Serializable {
    private Long id;
}
