package domain.validators;

import domain.GunAccessories;

public class AccessoryValidator implements Validator<GunAccessories>{
    @Override
    public void validate(GunAccessories entity) throws ValidatorException {
        if (entity == null)
            throw new ValidatorException("entity must not be null");
        if(entity.getPrice() <= 0)
            throw new ValidatorException("Price must be higher than 0");
        if(entity.getId() < 0)
            throw new ValidatorException("Id cannot be negative.");
    }
}
