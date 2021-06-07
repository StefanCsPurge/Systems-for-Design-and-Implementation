package domain.validators;

import domain.Ammunition;

public class AmmoValidator implements Validator<Ammunition>{
    @Override
    public void validate(Ammunition entity) throws ValidatorException {
        if (entity == null)
            throw new ValidatorException("entity must not be null");
        if(entity.getPrice() < 0)
            throw new ValidatorException("Price cannot be negative.");
        if(entity.getId() < 0)
            throw new ValidatorException("Id cannot be negative.");
        if(entity.getNoOfRounds() < 0)
            throw new ValidatorException("No. of rounds cannot be negative.");
        if(entity.getCaliber() < 0)
            throw new ValidatorException("Caliber cannot be negative.");
    }
}
