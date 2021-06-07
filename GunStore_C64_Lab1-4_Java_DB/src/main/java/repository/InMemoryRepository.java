package repository;

import domain.BaseEntity;
import domain.validators.Validator;

import java.util.*;

/**
 * @author The Commodore BOIZ.
 */
public class InMemoryRepository<ID, T extends BaseEntity<ID>> implements Repository<ID, T> {

    protected Map<ID, T> entities;
    protected Validator<T> validator;

    public InMemoryRepository(Validator<T> validator) {
        this.validator = validator;
        entities = new HashMap<>();
    }


    /**
     * find a specific element from the repo given its id
     * @param id id
     *            must be not null.
     * @return
     */
    @Override
    public Optional<T> findOne(ID id) {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        return Optional.ofNullable(entities.get(id));
    }


    /**
     * Get all the entities from the repo
     * @return hashset of all the entities in the repo
     */
    @Override
    public HashSet<T> findAll() {
        return new HashSet<>(entities.values());
    }


    /**
     * Saves an entity to the repository
     * @param entity T to be saved
     * @return If added successfully, the entity is returned
     * @throws Exception If the entity cannot be added to the repo.
     */
    @Override
    public Optional<T> save(T entity) throws Exception {
        if (entity == null) {
            throw new IllegalArgumentException("entity must not be null");
        }
        validator.validate(entity);
        return Optional.ofNullable(entities.putIfAbsent(entity.getId(), entity));
    }


    /**
     * Delete an entity with a given ID
     * @param  id the given ID
     * @return the deleted entity
     */
    @Override
    public Optional<T> delete(ID id) throws Exception {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        return Optional.ofNullable(entities.remove(id));
    }


    /**
     * update a specific entity
     * @param entity the entity to be updated (id must match one existant in the repo
     * @return an optional, the entity if it was successfully updated
     * @throws Exception is raised by the base class if the ID does not exist in the repo
     */
    @Override
    public Optional<T> update(T entity) throws Exception {
        if (entity == null) {
            throw new IllegalArgumentException("entity must not be null");
        }
        validator.validate(entity);
        return Optional.ofNullable(entities.computeIfPresent(entity.getId(), (k, v) -> entity));
    }
}
