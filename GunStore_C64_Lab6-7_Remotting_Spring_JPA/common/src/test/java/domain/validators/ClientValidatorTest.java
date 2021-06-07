package domain.validators;

import domain.Client;
import domain.exceptions.ValidatorException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class ClientValidatorTest {
    private static final Long ID = 1L;
    private static final String NAME = "Name1";
    private static final String CNP = "Cnp1";
    private static final float BUDGET = 123;


    private static ClientValidator clientValidator;
    private static Client client;

    @BeforeAll
    static void init() {
        clientValidator = new ClientValidator();
    }

    @BeforeEach
    public void setUp() {

    }

    @org.junit.jupiter.api.Test
    public void testNull(){
        Assertions.assertThrows(ValidatorException.class, ()->{clientValidator.validate(null);});
    }

    @org.junit.jupiter.api.Test
    public void testNegativeBudget(){
        Assertions.assertThrows(ValidatorException.class, ()-> {
            client = new Client(NAME, CNP, -2);
            clientValidator.validate(client);
        });
    }

    @org.junit.jupiter.api.Test
    public void testCnp(){
        Assertions.assertThrows(ValidatorException.class, ()-> {
            client = new Client(NAME, "24234234", BUDGET);
            clientValidator.validate(client);
        });
    }
}
