package ro.ubb.movieapp.core.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * author: radu
 */

@NamedEntityGraphs({
        @NamedEntityGraph(name = "MovieWithActors",
                attributeNodes = @NamedAttributeNode(value = "actors"))
})
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class Movie extends BaseEntity<Long> {
    private String title;

    private int year;

    //actors
    @OneToMany(orphanRemoval=true, cascade = CascadeType.ALL, mappedBy = "movie", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Actor> actors;

}
