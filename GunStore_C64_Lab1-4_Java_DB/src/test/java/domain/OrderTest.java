package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {
    private static final Long ID = 1L;
    private static final Long NEW_ID = 2L;
    private static final Long GUN_ID = 1L;
    private static final Long NEW_GUN_ID = 2L;
    private static final Long CLIENT_ID = 1L;
    private static final Long NEW_CLIENT_ID = 2L;


    private static Order order;

    @BeforeAll
    static void init() {
        order = new Order(ID, GUN_ID, CLIENT_ID);
    }

    @BeforeEach
    public void setUp() {
        order = new Order(ID, GUN_ID, CLIENT_ID);
    }

    @org.junit.jupiter.api.Test
    public void testGetGunId() {
        assertEquals(GUN_ID, order.getGunId());
    }

    @org.junit.jupiter.api.Test
    public void testGetClientId() {
        assertEquals(CLIENT_ID, order.getClientId());
    }

    @org.junit.jupiter.api.Test
    public void testEquals() {
        order = new Order(ID, GUN_ID, CLIENT_ID);
        Order order2 = new Order(ID, GUN_ID, CLIENT_ID);
        assertEquals(order, order2);

        Order order3 = new Order(NEW_ID, NEW_GUN_ID, NEW_CLIENT_ID);
        assertNotEquals(order, order3);
    }

    @org.junit.jupiter.api.Test
    public void testHash() {
        int hash = Objects.hash(GUN_ID, CLIENT_ID);
        int hash_code = order.hashCode();
        assertEquals(hash, hash_code);
    }

    @org.junit.jupiter.api.Test
    public void testToString() {
        String test_string = "Order{" +
                "ID=" + ID + ", " +
                "gunId=" + GUN_ID +
                ", clientId=" + CLIENT_ID +
                '}';
        String order_string = order.toString();
        assertEquals(test_string, order_string);
    }

    @org.junit.jupiter.api.Test
    public void testCSVString() {
        String test_string = ID + "," + GUN_ID + "," + CLIENT_ID;
        String order_string = order.getCSVString();
        assertEquals(test_string, order_string);
    }

    @org.junit.jupiter.api.Test
    public void testReadEntities() {
        Order testOrder = new Order();
        Order testOrder2 = new Order(99L,99L,99L);
        String order_to_read = "99,99,99";

        testOrder.readEntityCSV(order_to_read);
        assertEquals(testOrder, testOrder2);

    }
}
