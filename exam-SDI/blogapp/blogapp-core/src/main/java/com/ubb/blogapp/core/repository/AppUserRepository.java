package com.ubb.blogapp.core.repository;

import com.ubb.blogapp.core.model.AppUser;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;

public interface AppUserRepository extends BlogRepository<AppUser, Long> {

    @Query("select distinct u from AppUser u where u.id = ?1")
    @EntityGraph(value = "userWithFollowers", type = EntityGraph.EntityGraphType.LOAD)
    AppUser findWithFollowers(Long id);

    @Query("select distinct u from AppUser u where u.id = ?1")
    @EntityGraph(value = "userWithPostsAndFollowers", type = EntityGraph.EntityGraphType.LOAD)
    AppUser findWithPostsAndFollowers(Long id);

}
