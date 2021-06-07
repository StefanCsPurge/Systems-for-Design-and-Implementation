package domain;

import java.util.Objects;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class StoreOrderTest {
    private static final Long ID = 1L;
    private static final Long GUN_ID = 1L;
    private static final Long NEW_GUN_ID = 2L;
    private static final Long CLIENT_ID = 1L;
    private static final Long NEW_CLIENT_ID = 2L;


    private static StoreOrder storeOrder;

    @BeforeAll
    static void init() {
        storeOrder = new StoreOrder(GUN_ID, CLIENT_ID);
    }

    @BeforeEach
    public void setUp() {
        storeOrder = new StoreOrder(GUN_ID, CLIENT_ID);
        storeOrder.setId(ID);
    }

    @org.junit.jupiter.api.Test
    public void testGetGunId() {
        assertEquals(GUN_ID, storeOrder.getGunId());
    }

    @org.junit.jupiter.api.Test
    public void testGetClientId() {
        assertEquals(CLIENT_ID, storeOrder.getClientId());
    }

    @org.junit.jupiter.api.Test
    public void testEquals() {
        storeOrder = new StoreOrder(GUN_ID, CLIENT_ID);
        StoreOrder storeOrder2 = new StoreOrder(GUN_ID, CLIENT_ID);
        assertEquals(storeOrder, storeOrder2);

        StoreOrder storeOrder3 = new StoreOrder(NEW_GUN_ID, NEW_CLIENT_ID);
        assertNotEquals(storeOrder, storeOrder3);
    }

    @org.junit.jupiter.api.Test
    public void testHash() {
        int hash = Objects.hash(GUN_ID, CLIENT_ID);
        int hash_code = storeOrder.hashCode();
        assertEquals(hash, hash_code);
    }

    @org.junit.jupiter.api.Test
    public void testToString() {
        String test_string = "Order{" +
                "ID=" + ID + ", " +
                "gunId=" + GUN_ID +
                ", clientId=" + CLIENT_ID +
                '}';
        String order_string = storeOrder.toString();
        assertEquals(test_string, order_string);
    }

    @org.junit.jupiter.api.Test
    public void testCSVString() {
        String test_string = ID + "," + GUN_ID + "," + CLIENT_ID;
        String order_string = storeOrder.getCSVString();
        assertEquals(test_string, order_string);
    }

    @org.junit.jupiter.api.Test
    public void testReadEntities() {
        StoreOrder testStoreOrder = new StoreOrder();
        StoreOrder testStoreOrder2 = new StoreOrder(99L,99L);
        String order_to_read = "99,99,99";

        testStoreOrder.readEntityCSV(order_to_read);
        assertEquals(testStoreOrder, testStoreOrder2);

    }
}
