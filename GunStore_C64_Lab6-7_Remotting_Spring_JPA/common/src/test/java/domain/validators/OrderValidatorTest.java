package domain.validators;

import domain.GunAccessories;
import domain.StoreOrder;
import domain.exceptions.RepositoryException;
import domain.exceptions.ValidatorException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

public class OrderValidatorTest {
    private static final Long ID = 1L;
    private static final Long GUN_ID = 1L;
    private static final Long NEW_GUN_ID = 2L;
    private static final Long CLIENT_ID = 1L;
    private static final Long NEW_CLIENT_ID = 2L;


    private static OrderValidator orderValidator;

    @BeforeAll
    static void init() {
        orderValidator = new OrderValidator();
    }

    @BeforeEach
    public void setUp() {

    }

    @org.junit.jupiter.api.Test
    public void testNull() {
        Assertions.assertThrows(ValidatorException.class, ()-> {
            var order = new StoreOrder(GUN_ID, CLIENT_ID);
            orderValidator.validate(null);
        });
    }

    @org.junit.jupiter.api.Test
    public void testClientID() {
        Assertions.assertThrows(ValidatorException.class, ()-> {
            var order = new StoreOrder(GUN_ID, (long) -1);
            orderValidator.validate(order);
        });
    }

    @org.junit.jupiter.api.Test
    public void testGunID() {
        Assertions.assertThrows(ValidatorException.class, ()-> {
            var order = new StoreOrder((long) -1, CLIENT_ID);
            orderValidator.validate(order);
        });
    }

    @org.junit.jupiter.api.Test
    public void test() {
        Assertions.assertThrows(RepositoryException.class, ()-> {
            throw new RepositoryException("ana");
        });
    }

}
