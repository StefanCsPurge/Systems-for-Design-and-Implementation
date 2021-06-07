package ro.ubb.gunstore.core.model.validators;

import org.springframework.stereotype.Component;
import ro.ubb.gunstore.core.model.StoreOrder;
import ro.ubb.gunstore.core.model.exceptions.ValidatorException;

@Component
public class OrderValidator implements Validator<StoreOrder>{
    @Override
    public void validate(StoreOrder entity) throws ValidatorException {
        if (entity == null)
            throw new ValidatorException("entity must not be null");
//        if(entity.getClientId() < 0)
//            throw new ValidatorException("client id cannot be negative.");
//        if(entity.getGunId() < 0)
//            throw new ValidatorException("gun id cannot be negative.");
    }
}
