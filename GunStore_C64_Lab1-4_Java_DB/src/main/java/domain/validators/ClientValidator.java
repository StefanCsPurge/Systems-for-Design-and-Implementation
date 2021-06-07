package domain.validators;

import domain.Client;

public class ClientValidator implements Validator<Client>{
    @Override
    public void validate(Client entity) throws ValidatorException {
        if (entity == null)
            throw new ValidatorException("entity must not be null");
        if(entity.getBudget() < 0)
            throw new ValidatorException("Budget cannot be negative.");
        if(entity.getCnp().length() != 13)
            throw new ValidatorException("Cnp not valid.");
    }
}
