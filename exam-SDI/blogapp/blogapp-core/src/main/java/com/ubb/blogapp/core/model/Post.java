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
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class Post extends BaseEntity<Long>{
    private String title;
    private String content;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    private AppUser poster;

    @OneToMany(orphanRemoval=true, cascade = CascadeType.ALL, mappedBy = "post", fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Comment> comments;
}
