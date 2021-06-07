package ro.ubb.gunstore.core.model;

import lombok.*;

import javax.persistence.Entity;


/**
 * Object representing a gun.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Gun extends BaseEntity<Long> {
    private String model;
    private String manufacturer;
    private String type;
    private float weight;
    private float price;
    private Long timesSold;
}
