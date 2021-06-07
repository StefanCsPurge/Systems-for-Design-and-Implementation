package domain.validators;

import domain.exceptions.ValidatorException;

/**
 * Generic validator interface
 * @param <T> Type to be validated
 */
public interface Validator<T> {
    void validate(T entity) throws ValidatorException;
}
