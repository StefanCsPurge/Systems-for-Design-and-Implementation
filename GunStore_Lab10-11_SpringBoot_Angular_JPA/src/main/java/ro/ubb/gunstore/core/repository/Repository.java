package ro.ubb.gunstore.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ro.ubb.gunstore.core.model.BaseEntity;

import java.io.Serializable;

/**
 * Interface for generic CRUD operations on a repository for a specific type.
 */
@NoRepositoryBean
public interface Repository<ID extends Serializable, T extends BaseEntity<ID>>
        extends JpaRepository<T,ID> {
}
