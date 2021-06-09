package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import java.io.Serializable;

/**
 * Interface for generic CRUD operations on a repository for a specific type.
 */
@NoRepositoryBean
public interface Repository<Long extends Serializable, T>
        extends JpaRepository<T,Long> {
}
