package com.ubb.blogapp.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import com.ubb.blogapp.core.model.BaseEntity;

import java.io.Serializable;

/**
 * Interface for generic CRUD operations on a repository for a specific type.
 */
@NoRepositoryBean
public interface BlogRepository<T extends BaseEntity<ID>, ID extends Serializable>
        extends JpaRepository<T,ID> {
}
