package domain.validators;

import domain.Client;
import domain.GunAccessories;
import domain.exceptions.ValidatorException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

public class AccessoryValidatorTest {
    private static final Long ID = 1L;
    private static final Long NEW_ID = 2L;
    private static final String NAME = "Name1";
    private static final String NEW_NAME = "Name2";
    private static final String TYPE = "Type1";
    private static final String NEW_TYPE = "Type2";
    private static final String COMPANY = "Company1";
    private static final String NEW_COMPANY = "Company2";
    private static final float PRICE = 123;
    private static final float NEW_PRICE = 1234;

    private static AccessoryValidator accessoryValidator;

    @BeforeAll
    static void init() {
        accessoryValidator = new AccessoryValidator();
    }

    @BeforeEach
    void setUp(){
    }

    @org.junit.jupiter.api.Test
    public void testNull() {
        Assertions.assertThrows(ValidatorException.class, ()-> {
            var accessory = new GunAccessories(NAME, TYPE, COMPANY, PRICE);
            accessoryValidator.validate(null);
        });
    }

    @org.junit.jupiter.api.Test
    public void testPrice() {
        Assertions.assertThrows(ValidatorException.class, ()-> {
            var accessory = new GunAccessories(NAME, TYPE, COMPANY, -1);
            accessoryValidator.validate(accessory);
        });
    }


}
