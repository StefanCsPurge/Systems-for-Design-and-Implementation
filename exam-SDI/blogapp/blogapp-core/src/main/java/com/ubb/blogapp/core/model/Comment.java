package com.ubb.blogapp.core.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


/**
 * Object representing a client.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true, exclude = "post")
@ToString(callSuper = true, exclude = "post")
@Builder
public class Comment extends BaseEntity<Long>{
    private String text;

    @ManyToOne
    private Post post;
}
