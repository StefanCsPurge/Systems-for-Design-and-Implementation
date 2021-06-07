package domain.validators;

import domain.Ammunition;
import domain.StoreOrder;
import domain.exceptions.ValidatorException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

public class AmmoValidatorTest {
    private static final Long ID = 1L;
    private static final Long NEW_ID = 2L;
    private static final int ROUNDS = 20;
    private static final int NEW_ROUNDS = 30;
    private static final float CALIBER = 45;
    private static final float NEW_CALIBER = 300;
    private static final String MANUFACTURER = "Company1";
    private static final String NEW_MANUFACTURER = "Company2";
    private static final float PRICE = 123;
    private static final float NEW_PRICE = 1234;

    private static AmmoValidator ammoValidator;

    @BeforeAll
    static void init() {
        ammoValidator = new AmmoValidator();
    }

    @BeforeEach
    public void setUp() {

    }

    @org.junit.jupiter.api.Test
    public void testNull() {
        Assertions.assertThrows(ValidatorException.class, ()-> {
            var ammo = new Ammunition(ROUNDS, CALIBER, MANUFACTURER, PRICE);
            ammoValidator.validate(null);
        });
    }

    @org.junit.jupiter.api.Test
    public void testPrice() {
        Assertions.assertThrows(ValidatorException.class, ()-> {
            var ammo = new Ammunition(ROUNDS, CALIBER, MANUFACTURER, -1);
            ammoValidator.validate(ammo);
        });
    }

    @org.junit.jupiter.api.Test
    public void testRounds() {
        Assertions.assertThrows(ValidatorException.class, ()-> {
            var ammo = new Ammunition(-1, CALIBER, MANUFACTURER, PRICE);
            ammoValidator.validate(ammo);
        });
    }

    @org.junit.jupiter.api.Test
    public void testCaliber() {
        Assertions.assertThrows(ValidatorException.class, ()-> {
            var ammo = new Ammunition(ROUNDS, -1, MANUFACTURER, PRICE);
            ammoValidator.validate(ammo);
        });
    }

}
