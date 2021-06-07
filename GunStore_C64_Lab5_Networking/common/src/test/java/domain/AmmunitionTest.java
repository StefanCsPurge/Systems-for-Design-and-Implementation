package domain;



import java.util.Objects;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class AmmunitionTest {
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

    private static Ammunition ammunition;

    @BeforeAll
    static void init() {
        ammunition = new Ammunition(ID, ROUNDS, CALIBER, MANUFACTURER, PRICE);
    }

    @BeforeEach
    public void setUp() {
        ammunition =  new Ammunition(ID, ROUNDS, CALIBER, MANUFACTURER, PRICE);
    }

    @org.junit.jupiter.api.Test
    public void testGetId() {
        assertEquals(ID, ammunition.getId());
    }

    @org.junit.jupiter.api.Test
    public void testSetId() {
        ammunition.setId(NEW_ID);
        assertEquals(NEW_ID, ammunition.getId());
    }

    @org.junit.jupiter.api.Test
    public void testGetRounds() {
        assertEquals(ROUNDS, ammunition.getNoOfRounds());
    }

    @org.junit.jupiter.api.Test
    public void testGetCaliber() {
        assertEquals(CALIBER, ammunition.getCaliber(), 0.0001);
    }

    @org.junit.jupiter.api.Test
    public void testGetManufacturer() {
        assertEquals(MANUFACTURER, ammunition.getManufacturer());
    }

    @org.junit.jupiter.api.Test
    public void testGetPrice() {
        assertEquals(PRICE, ammunition.getPrice(),0.0001);
    }

    @org.junit.jupiter.api.Test
    public void testEquals() {
        ammunition = new Ammunition(ID, ROUNDS, CALIBER, MANUFACTURER, PRICE);
        Ammunition ammunition2 = new Ammunition(ID, ROUNDS, CALIBER, MANUFACTURER, PRICE);
        assertEquals(ammunition, ammunition2);

        Ammunition ammunition3 = new Ammunition(NEW_ID, NEW_ROUNDS, NEW_CALIBER, NEW_MANUFACTURER, NEW_PRICE);
        assertNotEquals(ammunition, ammunition3);
    }

    @org.junit.jupiter.api.Test
    public void testHash() {
        int hash = Objects.hash(ROUNDS, CALIBER, MANUFACTURER, PRICE);
        int hash_code = ammunition.hashCode();
        assertEquals(hash, hash_code);
    }

    @org.junit.jupiter.api.Test
    public void testToString() {
        String test_string = "Ammo{" +
                "ID=" + ID +
                ", noOfRounds=" + ROUNDS +
                ", caliber=" + CALIBER +
                ", manufacturer=" + MANUFACTURER +
                ", price=" + PRICE +
                '}';
        String ammunition_string = ammunition.toString();
        assertEquals(test_string, ammunition_string);
    }

    @org.junit.jupiter.api.Test
    public void testCSVString() {
        String test_string = ID + "," + ROUNDS + "," + CALIBER + "," + MANUFACTURER + "," + PRICE;
        String ammunition_string = ammunition.getCSVString();
        assertEquals(test_string, ammunition_string);
    }

    @org.junit.jupiter.api.Test
    public void testReadEntities() {
        Ammunition testammunition = new Ammunition();
        Ammunition testammunition2 = new Ammunition(99L,100,300,"testManufacturer",99);
        String ammunition_to_read = "99,100,300,testManufacturer,99";

        testammunition.readEntityCSV(ammunition_to_read);
        assertEquals(testammunition, testammunition2);

    }
}
