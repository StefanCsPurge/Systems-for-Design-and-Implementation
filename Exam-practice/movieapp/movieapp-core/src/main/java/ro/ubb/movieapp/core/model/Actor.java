package ro.ubb.movieapp.core.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * author: radu
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(callSuper = true, exclude = "movie")
public class Actor extends BaseEntity<Long> {
    private String name;

    private int rating;

    @ManyToOne
    @ToString.Exclude
    private Movie movie;
}
