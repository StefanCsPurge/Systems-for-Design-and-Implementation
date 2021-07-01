package com.ubb.blogapp.core.model;

import lombok.*;

import javax.persistence.*;


/**
 * Object representing a client.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true, exclude = "appUser")
@ToString(callSuper = true)
@Builder
public class Follower extends BaseEntity<Long>{
    private String name;
    private String birthday;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Address address;

    @ManyToOne
    private AppUser appUser;
}
