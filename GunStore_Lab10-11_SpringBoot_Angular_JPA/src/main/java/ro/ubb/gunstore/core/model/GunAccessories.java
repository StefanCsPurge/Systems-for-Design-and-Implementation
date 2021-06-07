package ro.ubb.gunstore.core.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


/**
 * Gun accessories object.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class GunAccessories extends BaseEntity<Long>{
    private String name;
    private String type;
    private String company;
    private float price;

    @ManyToOne
    private Gun gun;

    @Override
    public String toString() {
        return "GunAccessory{" +
                "ID=" + super.getId() +
                ", name=" + this.name +
                ", type=" + this.type +
                ", company=" + this.company +
                ", price=" + this.price +
                ", gun=" + this.gun.getId() +
                '}';
    }
}
