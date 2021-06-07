package domain.validators;

import domain.Client;
import domain.Gun;
import domain.exceptions.ValidatorException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

public class GunValidatorTest {

    private static final Long ID = 1L;
    private static final Long NEW_ID = 2L;
    private static final String MODEL = "model1";
    private static final String NEW_MODEL = "model2";
    private static final String TYPE = "type1";
    private static final String NEW_TYPE = "type2";
    private static final String MANUFACTURER = "man1";
    private static final String NEW_MANUFACTURER = "man2";
    private static final float WEIGHT = 22;
    private static final float NEW_WEIGHT = 422;
    private static final float PRICE = 222;
    private static final float NEW_PRICE = 4222;

    private static GunValidator gunValidator;

    @BeforeAll
    static void init() {
        gunValidator = new GunValidator();
    }

    @BeforeEach
    public void setUp() {

    }

    @org.junit.jupiter.api.Test
    public void testNull() {
        Assertions.assertThrows(ValidatorException.class, ()-> {
            var gun = new Gun(MODEL, MANUFACTURER, TYPE, WEIGHT, PRICE);
            gunValidator.validate(null);
        });
    }

    @org.junit.jupiter.api.Test
    public void testPrice() {
        Assertions.assertThrows(ValidatorException.class, ()-> {
            var gun = new Gun(MODEL, MANUFACTURER, TYPE, WEIGHT, -1);
            gunValidator.validate(gun);
        });
    }

    @org.junit.jupiter.api.Test
    public void testWeight() {
        Assertions.assertThrows(ValidatorException.class, ()-> {
            var gun = new Gun(MODEL, MANUFACTURER, TYPE, -1, PRICE);
            gunValidator.validate(gun);
        });
    }

}
