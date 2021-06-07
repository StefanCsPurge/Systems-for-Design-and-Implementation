package service;

import domain.Ammunition;
import domain.GunAccessories;
import domain.validators.AccessoryValidator;
import domain.validators.AmmoValidator;
import service.*;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import repository.InMemoryRepository;

import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;
@SuppressWarnings("OptionalGetWithoutIsPresent")

public class AmmoServiceTest {

    private static AmmoService service;
    private static Ammunition ammo1;
    private static Ammunition ammo2;
    private static final Long ID = 1L;
    private static final Long ID2 = 2L;
    private static final Long NEW_ID = 3L;
    private static final int noOfRounds = 123;
    private static final int noOfRounds2 = 124;
    private static final int NEW_noOfRounds = 555;
    private static final float caliber = 123;
    private static final float caliber2 = 124;
    private static final float NEW_caliber = 555;
    private static final String MANUFACTURER = "MANUFACTURER1";
    private static final String MANUFACTURER2 = "MANUFACTURER2";
    private static final String NEW_MANUFACTURER = "MANUFACTURER3";
    private static final float PRICE = 123;
    private static final float PRICE2 = 124;
    private static final float NEW_PRICE = 555;



    @BeforeAll
    static void init()   {
        ammo1 = new Ammunition(ID, noOfRounds, caliber, MANUFACTURER,PRICE);
        ammo2 = new Ammunition(ID2, noOfRounds2, caliber2, MANUFACTURER2,PRICE2);
    }

    @BeforeEach
    void setUp() {
        ammo1 = new Ammunition(ID, noOfRounds, caliber, MANUFACTURER,PRICE);
        ammo2 = new Ammunition(ID2, noOfRounds2, caliber2, MANUFACTURER2,PRICE2);
        service = new AmmoService(new InMemoryRepository<>(new AmmoValidator()));
        assertDoesNotThrow(()->service.addAmmo(ammo1), "SHOULD NOT THROW EXCEPTION");
    }

    @org.junit.jupiter.api.Test
    void testAddAmmo() {
        assertDoesNotThrow(()->service.addAmmo(ammo2), "SHOULD NOT THROW EXCEPTION");
        assertTrue(service.getAllAmmo().stream().anyMatch(z->z.equals(ammo2)), "THE AMMUNITION SHOULD HAVE BEEN ADDED");

    }

    @org.junit.jupiter.api.Test
    void testAddAmmo2() {
        assertDoesNotThrow(()->service.addAmmo(ammo2), "SHOULD NOT THROW EXCEPTION");
        assertTrue(StreamSupport.stream(service.getAllAmmo().spliterator(), false).anyMatch(z->z.equals(ammo2)), "THE Ammo SHOULD HAVE BEEN ADDED");
    }

    @org.junit.jupiter.api.Test
    void testRemoveAmmunition() {
        assertDoesNotThrow(()->service.delete(ammo1.getId()), "SHOULD NOT FAIL");
        assertFalse(StreamSupport.stream(service.getAllAmmo().spliterator(), false).anyMatch(v->v.equals(ammo1)), "THE AMMO SHOULD HAVE BEEN DELETED");
    }

    @org.junit.jupiter.api.Test
    void testUpdateAmmunition(){
        assertDoesNotThrow(()->service.update(ammo1), "SHOULD NOT FAIL");
        assertEquals(service.getAllAmmo().stream()
                .filter(z->z.getId().equals(ammo1.getId()))
                .findFirst().get().getPrice(), ammo1.getPrice());
    }


    @org.junit.jupiter.api.Test
    void testGetAll() {
        assertEquals(1L, service.getAllAmmo().spliterator().getExactSizeIfKnown());
        assertTrue(StreamSupport.stream(service.getAllAmmo().spliterator(), false).anyMatch(v->v.equals(ammo1)));
    }

}
