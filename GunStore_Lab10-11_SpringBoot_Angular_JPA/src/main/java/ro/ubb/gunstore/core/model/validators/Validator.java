package ro.ubb.gunstore.core.model.validators;


import ro.ubb.gunstore.core.model.exceptions.ValidatorException;

/**
 * Generic validator interface
 * @param <T> Type to be validated
 */
public interface Validator<T> {
    void validate(T entity) throws ValidatorException;
}
