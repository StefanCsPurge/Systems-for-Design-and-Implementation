package ro.ubb.gunstore.core.model;

import lombok.*;

import javax.persistence.Entity;


/**
 * Gun accessories object.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GunAccessories extends BaseEntity<Long>{
    private String name;
    private String type;
    private String company;
    private float price;
    private Long gunId;
}
