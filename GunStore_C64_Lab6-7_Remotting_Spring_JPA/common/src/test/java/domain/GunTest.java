package domain;



import java.util.Objects;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class GunTest {
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

    private static Gun gun;

    @BeforeAll
    static void init() {
        gun = new Gun(MODEL, MANUFACTURER, TYPE, WEIGHT, PRICE);
    }

    @BeforeEach
    public void setUp() {
        gun = new Gun(MODEL, MANUFACTURER, TYPE, WEIGHT, PRICE);
        gun.setId(ID);
    }

    @org.junit.jupiter.api.Test
    public void testGetModel() {
        assertEquals(MODEL, gun.getModel());
    }

    @org.junit.jupiter.api.Test
    public void testSetModel() {
        gun.setModel(NEW_MODEL);
        assertEquals(NEW_MODEL, gun.getModel());
    }

    @org.junit.jupiter.api.Test
    public void testGetId() {
        assertEquals(ID, gun.getId());
    }

    @org.junit.jupiter.api.Test
    public void testSetId() {
        gun.setId(NEW_ID);
        assertEquals(NEW_ID, gun.getId());
    }

    @org.junit.jupiter.api.Test
    public void testGetManufacturer() {
        assertEquals(MANUFACTURER, gun.getManufacturer());
    }

    @org.junit.jupiter.api.Test
    public void testSetManufacturer() {
        gun.setManufacturer(NEW_MANUFACTURER);
        assertEquals(NEW_MANUFACTURER, gun.getManufacturer());
    }

    @org.junit.jupiter.api.Test
    public void testGetType() {
        assertEquals(TYPE, gun.getType());
    }

    @org.junit.jupiter.api.Test
    public void testSetType() {
        gun.setType(NEW_TYPE);
        assertEquals(NEW_TYPE, gun.getType());
    }

    @org.junit.jupiter.api.Test
    public void testGetWeight() {
        assertEquals(WEIGHT, gun.getWeight(),0.0001);
    }

    @org.junit.jupiter.api.Test
    public void testSetWeight() {
        gun.setWeight(NEW_WEIGHT);
        assertEquals(NEW_WEIGHT, gun.getWeight(),0.0001);
    }

    @org.junit.jupiter.api.Test
    public void testGetPrice() {
        assertEquals(PRICE, gun.getPrice(),0.0001);
    }

    @org.junit.jupiter.api.Test
    public void testSetPrice() {
        gun.setPrice(NEW_PRICE);
        assertEquals(NEW_PRICE, gun.getPrice(),0.0001);
    }

    @org.junit.jupiter.api.Test
    public void testEquals() {
        gun = new Gun(MODEL, MANUFACTURER, TYPE, WEIGHT, PRICE);
        Gun gun2 = new Gun(MODEL, MANUFACTURER, TYPE, WEIGHT, PRICE);
        assertEquals(gun, gun2);

        Gun gun3 = new Gun(NEW_MODEL, MANUFACTURER, TYPE, WEIGHT, PRICE);
        assertNotEquals(gun, gun3);
    }

    @org.junit.jupiter.api.Test
    public void testHash() {
        int hash = Objects.hash(MODEL, MANUFACTURER, TYPE, WEIGHT, PRICE);
        int hash_code = gun.hashCode();
        assertEquals(hash, hash_code);
    }

    @org.junit.jupiter.api.Test
    public void testToString() {
        String test_string = "Gun{" +
                "ID='" + ID + '\'' +
                ", model='" + MODEL + '\'' +
                ", manufacturer='" + MANUFACTURER + '\'' +
                ", type='" + TYPE + '\'' +
                ", weight=" + WEIGHT +
                ", price=" + PRICE +
                '}';
        String gun_string = gun.toString();
        assertEquals(test_string, gun_string);
    }


    @org.junit.jupiter.api.Test
    public void testReadEntities() {
        Gun testGun = new Gun();
        Gun testGun2 = new Gun("testModel","testManufacturer","testType",99,99);
        String gun_to_read = "99,testModel,testManufacturer,testType,99,99";

        testGun.readEntityCSV(gun_to_read);
        assertEquals(testGun, testGun2);

    }
}
