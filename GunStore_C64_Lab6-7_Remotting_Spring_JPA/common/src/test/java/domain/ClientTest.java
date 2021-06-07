package domain;


import java.util.Objects;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {
    private static final Long ID = 1L;
    private static final Long NEW_ID = 2L;
    private static final String NAME = "Name1";
    private static final String NEW_NAME = "Name2";
    private static final String CNP = "Cnp1";
    private static final String NEW_CNP = "Cnp2";
    private static final float BUDGET = 123;
    private static final float NEW_BUDGET = 1234;

    private static Client client;

    @BeforeAll
    static void init() {
        client = new Client(NAME, CNP, BUDGET);
    }

    @BeforeEach
    public void setUp() {
        client = new Client(NAME, CNP, BUDGET);
        client.setId(ID);
    }

    @org.junit.jupiter.api.Test
    public void testGetId() {
        assertEquals(ID, client.getId());
    }

    @org.junit.jupiter.api.Test
    public void testSetId() {
        client.setId(NEW_ID);
        assertEquals(NEW_ID, client.getId());
    }

    @org.junit.jupiter.api.Test
    public void testGetName() {
        assertEquals(NAME, client.getName());
    }

    @org.junit.jupiter.api.Test
    public void testSetName() {
        client.setName(NEW_NAME);
        assertEquals(NEW_NAME, client.getName());
    }

    @org.junit.jupiter.api.Test
    public void testGetCnp() {
        assertEquals(CNP, client.getCnp());
    }

    @org.junit.jupiter.api.Test
    public void testSetCnp() {
        client.setCnp(NEW_CNP);
        assertEquals(NEW_CNP, client.getCnp());
    }

    @org.junit.jupiter.api.Test
    public void testGetBudget() {
        assertEquals(BUDGET, client.getBudget(),0.0001);
    }

    @org.junit.jupiter.api.Test
    public void testSetBudget() {
        client.setBudget(NEW_BUDGET);
        assertEquals(NEW_BUDGET, client.getBudget(),0.0001);
    }

    @org.junit.jupiter.api.Test
    public void testEquals() {
        client = new Client(NAME, CNP, BUDGET);
        Client client2 = new Client(NAME, CNP, BUDGET);
        assertEquals(client, client2);

        Client client3 = new Client(NEW_NAME, CNP, BUDGET);
        assertNotEquals(client, client3);
    }

    @org.junit.jupiter.api.Test
    public void testHash() {
        int hash = Objects.hash(NAME, CNP, BUDGET);
        int hash_code = client.hashCode();
        assertEquals(hash, hash_code);
    }

    @org.junit.jupiter.api.Test
    public void testToString() {
        String test_string = "Client{" +
                "ID=" + ID + ", " +
                "name='" + NAME + '\'' +
                ", cnp='" + CNP + '\'' +
                ", budget=" + BUDGET +
                '}';
        String client_string = client.toString();
        assertEquals(test_string, client_string);
    }

    @org.junit.jupiter.api.Test
    public void testCSVString() {
        String test_string = ID + "," + NAME + "," + CNP + "," + BUDGET;
        String client_string = client.getCSVString();
        assertEquals(test_string, client_string);
    }

    @org.junit.jupiter.api.Test
    public void testReadEntities() {
        Client testClient = new Client();
        Client testClient2 = new Client("testName","1234567890123",999);
        String client_to_read = "99,testName,1234567890123,999";

        testClient.readEntityCSV(client_to_read);
        assertEquals(testClient, testClient2);

    }
}
