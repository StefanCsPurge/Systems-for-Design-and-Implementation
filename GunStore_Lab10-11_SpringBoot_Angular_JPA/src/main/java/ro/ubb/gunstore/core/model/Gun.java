package ro.ubb.gunstore.core.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Object representing a gun.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class Gun extends BaseEntity<Long> {
    private String model;
    private String manufacturer;
    private String type;
    private float weight;
    private float price;
    private Integer timesSold;

    @Builder.Default
    @OneToMany(orphanRemoval=true, mappedBy = "ordered_gun", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<StoreOrder> gun_orders = new HashSet<>();

    @OneToMany(orphanRemoval=true, cascade = CascadeType.ALL, mappedBy = "gun", fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<GunAccessories> gunAccessories;
}
