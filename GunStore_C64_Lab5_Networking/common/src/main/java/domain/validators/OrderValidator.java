package domain.validators;

import domain.Order;
import domain.exceptions.ValidatorException;

public class OrderValidator implements Validator<Order>{
    @Override
    public void validate(Order entity) throws ValidatorException {
        if (entity == null)
            throw new ValidatorException("entity must not be null");
        if(entity.getId() < 0)
            throw new ValidatorException("Id cannot be negative.");
        if(entity.getClientId() < 0)
            throw new ValidatorException("client id cannot be negative.");
        if(entity.getGunId() < 0)
            throw new ValidatorException("gun id cannot be negative.");
    }
}
