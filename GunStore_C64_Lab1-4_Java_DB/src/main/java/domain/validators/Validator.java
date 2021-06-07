package domain.validators;

/**
 * Generic validator interface
 * @param <T> Type to be validated
 */
public interface Validator<T> {
    void validate(T entity) throws ValidatorException;
}
