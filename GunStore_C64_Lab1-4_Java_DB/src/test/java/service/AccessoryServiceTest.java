package service;

import domain.GunAccessories;
import domain.validators.AccessoryValidator;
import service.*;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import repository.InMemoryRepository;

import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;
@SuppressWarnings("OptionalGetWithoutIsPresent")

public class AccessoryServiceTest {

    private static AccessoryService service;
    private static GunAccessories accessory1;
    private static GunAccessories accessory2;
    private static final Long ID = 1L;
    private static final Long ID2 = 2L;
    private static final Long NEW_ID = 3L;
    private static final String NAME = "Name1";
    private static final String NAME2 = "Name2";
    private static final String NEW_NAME = "Name3";
    private static final String TYPE = "Type1";
    private static final String TYPE2 = "Type2";
    private static final String NEW_TYPE = "Type3";
    private static final String COMPANY = "Company1";
    private static final String COMPANY2 = "Company2";
    private static final String NEW_COMPANY = "Company3";
    private static final float PRICE = 123;
    private static final float PRICE2 = 124;
    private static final float NEW_PRICE = 555;



    @BeforeAll
    static void init()   {
    accessory1 = new GunAccessories(ID,NAME,TYPE,COMPANY,PRICE);
    accessory2 = new GunAccessories(ID2,NAME2,TYPE2,COMPANY2,PRICE2);
    GunAccessories newAccessory =  new GunAccessories(NEW_ID,NEW_NAME,NEW_TYPE,NEW_COMPANY,NEW_PRICE);
    }

    @BeforeEach
    void setUp() {
        accessory1 = new GunAccessories(ID,NAME,TYPE,COMPANY,PRICE);
        accessory2 = new GunAccessories(ID2,NAME2,TYPE2,COMPANY2,PRICE2);
        GunAccessories newAccessory =  new GunAccessories(NEW_ID,NEW_NAME,NEW_TYPE,NEW_COMPANY,NEW_PRICE);
        service = new AccessoryService(new InMemoryRepository<>(new AccessoryValidator()));
        assertDoesNotThrow(()->service.addAccessory(accessory1), "SHOULD NOT THROW EXCEPTION");
    }

    @org.junit.jupiter.api.Test
     void testAddAccessory() {
        assertDoesNotThrow(()->service.addAccessory(accessory2), "SHOULD NOT THROW EXCEPTION");
        assertTrue(service.getAllAccessories().stream().anyMatch(z->z.equals(accessory2)), "THE ACCESSORY SHOULD HAVE BEEN ADDED");

    }

    @org.junit.jupiter.api.Test
    void testAddAccessory2() {
        assertDoesNotThrow(()->service.addAccessory(accessory2), "SHOULD NOT THROW EXCEPTION");
        assertTrue(StreamSupport.stream(service.getAllAccessories().spliterator(), false).anyMatch(z->z.equals(accessory2)), "THE ACCESSORY SHOULD HAVE BEEN ADDED");
    }

    @org.junit.jupiter.api.Test
    void testRemoveAccessory() {
        assertDoesNotThrow(()->service.delete(accessory1.getId()), "SHOULD NOT FAIL");
        assertFalse(StreamSupport.stream(service.getAllAccessories().spliterator(), false).anyMatch(v->v.equals(accessory1)), "THE ACCESSORY SHOULD HAVE BEEN DELETED");
    }

    @org.junit.jupiter.api.Test
    void testUpdateAccessory(){
        assertDoesNotThrow(()->service.update(accessory1), "SHOULD NOT FAIL");
        assertEquals(service.getAllAccessories().stream()
                .filter(z->z.getId().equals(accessory1.getId()))
                .findFirst().get().getPrice(), accessory1.getPrice());
    }


    @org.junit.jupiter.api.Test
    void testFilterAccessoriesByCompany() {
        StreamSupport.stream(service.filterByCompany(NEW_COMPANY).spliterator(), false)
                .findAny()
                .ifPresent(z->fail("There is no accessory by that company"));
        accessory2 = new GunAccessories(ID2,NAME2,TYPE2,COMPANY2,PRICE2);
        assertDoesNotThrow(()->service.addAccessory(accessory2), "SHOULD NOT THROW EXCEPTION");
        assertEquals(StreamSupport.stream(service.filterByCompany(COMPANY2).spliterator(), false)
                .findFirst().get(), accessory2, "FILTER SHOULD RETURN THE MALE VISITOR");
    }
    @org.junit.jupiter.api.Test
    void testFilterAccessoriesByPrice() {
        StreamSupport.stream(service.filterByPrice(1,100).spliterator(), false)
                .findAny()
                .ifPresent(z->fail("There is no accessory by that company"));
        assertEquals(StreamSupport.stream(service.filterByPrice(1,1000).spliterator(), false)
                .findFirst().get(), accessory1, "FILTER SHOULD RETURN THE MALE VISITOR");
    }
    @org.junit.jupiter.api.Test
    void testGetAll() {
        assertEquals(1L, service.getAllAccessories().spliterator().getExactSizeIfKnown());
        assertTrue(StreamSupport.stream(service.getAllAccessories().spliterator(), false).anyMatch(v->v.equals(accessory1)));
    }

}
