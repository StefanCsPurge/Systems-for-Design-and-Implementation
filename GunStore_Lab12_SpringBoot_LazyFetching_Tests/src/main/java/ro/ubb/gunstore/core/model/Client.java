package ro.ubb.gunstore.core.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


/**
 * Object representing a client.
 */
@NamedEntityGraphs({
        @NamedEntityGraph(name = "clientWithOrders",
                attributeNodes = @NamedAttributeNode(value = "client_orders", subgraph = "orderWithGun"),
                subgraphs = @NamedSubgraph(name = "orderWithGun",
                        attributeNodes = @NamedAttributeNode(value = "ordered_gun")
                )
        )
})
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class Client extends BaseEntity<Long>{
    private String name;
    private String cnp;
    private float budget;

    @Embedded  // one-to-one
    private Address address;

    @Builder.Default
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<StoreOrder> client_orders = new HashSet<>();
}
