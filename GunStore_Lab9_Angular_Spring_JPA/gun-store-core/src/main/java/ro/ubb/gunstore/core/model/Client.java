package ro.ubb.gunstore.core.model;

import lombok.*;

import javax.persistence.Entity;


/**
 * Object representing a client.
 */
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
}
