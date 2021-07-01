package com.ubb.blogapp.core.model;

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
public class Address extends BaseEntity<Long>{
    private String city;
    private String street;
}
