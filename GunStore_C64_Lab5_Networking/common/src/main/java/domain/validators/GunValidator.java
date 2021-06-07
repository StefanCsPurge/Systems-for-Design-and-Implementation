package domain.validators;

import domain.Gun;
import domain.exceptions.ValidatorException;

public class GunValidator implements Validator<Gun>{
    @Override
    public void validate(Gun entity) throws ValidatorException {
        if (entity == null)
            throw new ValidatorException("entity must not be null");
        if(entity.getPrice() < 0)
            throw new ValidatorException("Price cannot be negative.");
        if(entity.getId() < 0)
            throw new ValidatorException("Id cannot be negative.");
        if(entity.getWeight() < 0)
            throw new ValidatorException("Weight cannot be negative.");
    }
}
