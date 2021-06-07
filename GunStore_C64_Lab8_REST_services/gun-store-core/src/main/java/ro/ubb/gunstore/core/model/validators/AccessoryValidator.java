package ro.ubb.gunstore.core.model.validators;

import org.springframework.stereotype.Component;
import ro.ubb.gunstore.core.model.GunAccessories;
import ro.ubb.gunstore.core.model.exceptions.ValidatorException;

@Component
public class AccessoryValidator implements Validator<GunAccessories>{
    @Override
    public void validate(GunAccessories entity) throws ValidatorException {
        if (entity == null)
            throw new ValidatorException("entity must not be null");
        if(entity.getPrice() <= 0)
            throw new ValidatorException("Price must be higher than 0");
    }
}
