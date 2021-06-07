package ro.ubb.gunstore.core.model.validators;

import org.springframework.stereotype.Component;
import ro.ubb.gunstore.core.model.Gun;
import ro.ubb.gunstore.core.model.exceptions.ValidatorException;

@Component
public class GunValidator implements Validator<Gun>{
    @Override
    public void validate(Gun entity) throws ValidatorException {
        if (entity == null)
            throw new ValidatorException("entity must not be null");
        if(entity.getPrice() < 0)
            throw new ValidatorException("Price cannot be negative.");
        if(entity.getWeight() < 0)
            throw new ValidatorException("Weight cannot be negative.");
    }
}
