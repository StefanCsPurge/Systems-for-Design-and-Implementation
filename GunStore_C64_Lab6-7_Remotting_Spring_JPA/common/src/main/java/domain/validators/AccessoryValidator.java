package domain.validators;

import domain.GunAccessories;
import domain.exceptions.ValidatorException;
import org.springframework.stereotype.Component;

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
