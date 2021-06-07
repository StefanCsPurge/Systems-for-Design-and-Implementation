package domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

public class AccessoryTest {
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

    private static GunAccessories accessory;

    @BeforeAll
    static void init() {
        accessory = new GunAccessories(NAME, TYPE, COMPANY, PRICE);
    }

    @BeforeEach
    void setUp(){
        accessory = new GunAccessories(NAME, TYPE, COMPANY, PRICE);
        accessory.setId(ID);
    }

    @org.junit.jupiter.api.Test
    public void testGetId() {
        assertEquals(ID, accessory.getId());
    }

    @org.junit.jupiter.api.Test
    public void testSetId() {
        accessory.setId(NEW_ID);
        assertEquals(NEW_ID, accessory.getId());
    }

    @org.junit.jupiter.api.Test
    public void testGetName() {
        assertEquals(NAME, accessory.getName());
    }

    @org.junit.jupiter.api.Test
    public void testSetName() {
        accessory.setName(NEW_NAME);
        assertEquals( NEW_NAME, accessory.getName());
    }

    @org.junit.jupiter.api.Test
    public void testGetTypes() {
        assertEquals(TYPE, accessory.getType());
    }

    @org.junit.jupiter.api.Test
    public void testSetTypes() {
        accessory.setType(NEW_TYPE);
        assertEquals(NEW_TYPE, accessory.getType());
    }

    @org.junit.jupiter.api.Test
    public void testGetCompany() {
        assertEquals(COMPANY, accessory.getCompany());
    }

    @org.junit.jupiter.api.Test
    public void testSetCompany() {
        accessory.setCompany(NEW_COMPANY);
        assertEquals(NEW_COMPANY, accessory.getCompany());
    }

    @org.junit.jupiter.api.Test
    public void testGetPrice() {
        assertEquals(PRICE, accessory.getPrice(),0.0001);
    }

    @org.junit.jupiter.api.Test
    public void testSetPrice() {
        accessory.setPrice(NEW_PRICE);
        assertEquals(NEW_PRICE, accessory.getPrice(),0.0001);
    }

    @org.junit.jupiter.api.Test
    public void testEquals() {
        accessory = new GunAccessories(NAME, TYPE, COMPANY, PRICE);
        GunAccessories accessory2 =new GunAccessories(NAME, TYPE, COMPANY, PRICE);
        assertEquals(accessory, accessory2);

        GunAccessories accessory3 = new GunAccessories(NEW_NAME, TYPE, COMPANY, PRICE);
        assertNotEquals(accessory, accessory3);
    }

    @org.junit.jupiter.api.Test
    public void testHash() {
        int hash = Objects.hash(NAME, TYPE, COMPANY, PRICE);
        int hash_code = accessory.hashCode();
        assertEquals(hash, hash_code);
    }

    @org.junit.jupiter.api.Test
    public void testToString() {
        String test_string = "GunAccessories{" +
                "ID='" + ID + '\'' +
                "name='" + NAME + '\'' +
                ", type='" + TYPE + '\'' +
                ", company='" + COMPANY + '\'' +
                ", price=" + PRICE +
                '}';
        String accessory_string = accessory.toString();
        assertEquals(test_string, accessory_string);
    }

    @org.junit.jupiter.api.Test
    public void testCSVString() {
        String test_string = ID + "," + NAME + "," + TYPE + "," + COMPANY + "," + PRICE;
        String accessory_string = accessory.getCSVString();
        assertEquals(test_string, accessory_string);
    }

    @org.junit.jupiter.api.Test
    public void testReadEntities() {
        GunAccessories testAccessory = new GunAccessories();
        GunAccessories testAccessory2 = new GunAccessories("testName","testType","testCompany",99);
        String accessory_to_read = "99,testName,testType,testCompany,99";

        testAccessory.readEntityCSV(accessory_to_read);
        assertEquals(testAccessory, testAccessory2);

    }
}

