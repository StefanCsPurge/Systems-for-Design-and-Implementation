package com.ubb.blogapp.core.model;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


/**
 * Object representing a client.
 */
@NamedEntityGraphs({
        @NamedEntityGraph(name = "userWithFollowers",
                attributeNodes = @NamedAttributeNode(value = "followers")
        ),

        @NamedEntityGraph(name = "userWithPostsAndFollowers",
                attributeNodes = {@NamedAttributeNode(value = "posts", subgraph = "postWithComments"), @NamedAttributeNode(value = "followers")},
                subgraphs = @NamedSubgraph(name = "postWithComments", attributeNodes = @NamedAttributeNode(value = "comments"))
        )
})
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class AppUser extends BaseEntity<Long>{
    private String name;
    private String birthday;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Address address;

    @OneToMany(orphanRemoval=true, cascade = CascadeType.ALL, mappedBy = "appUser", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Follower> followers;

    @OneToMany(orphanRemoval=true, cascade = CascadeType.ALL, mappedBy = "poster", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Fetch(value = FetchMode.SUBSELECT)
    private Set<Post> posts;
}
