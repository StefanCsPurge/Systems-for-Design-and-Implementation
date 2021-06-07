package ro.ubb.gunstore.core.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class StoreOrder extends BaseEntity<Long> {

    @SuppressWarnings("JpaDataSourceORMInspection")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    @EqualsAndHashCode.Exclude
    private Client client;

    @SuppressWarnings("JpaDataSourceORMInspection")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "gun_id")
    @EqualsAndHashCode.Exclude
    private Gun ordered_gun;

    @Override
    public String toString() {
        return "StoreOrder{" +
                "ID=" + super.getId() +
                ", client=" + client.getId() +
                ", gun=" + ordered_gun.getId() +
                '}';
    }
}
