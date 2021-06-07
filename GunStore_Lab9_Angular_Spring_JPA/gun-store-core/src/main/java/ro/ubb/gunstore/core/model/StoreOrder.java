package ro.ubb.gunstore.core.model;

import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StoreOrder extends BaseEntity<Long> {
    Long gunId;
    Long clientId;
}
