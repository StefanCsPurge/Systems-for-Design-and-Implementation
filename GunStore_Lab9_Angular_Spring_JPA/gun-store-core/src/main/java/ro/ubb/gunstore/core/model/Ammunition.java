package ro.ubb.gunstore.core.model;

import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Ammunition extends BaseEntity<Long> {
    private int noOfRounds;
    private float caliber;
    private String manufacturer;
    private float price;
}
