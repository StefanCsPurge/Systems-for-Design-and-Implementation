package service;

import domain.Client;
import domain.Gun;
import domain.Order;
import domain.GunAccessories;
import domain.validators.ClientValidator;
import domain.validators.GunValidator;
import domain.validators.OrderValidator;
import org.junit.jupiter.api.Test;
import repository.Repository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import repository.InMemoryRepository;

import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;
@SuppressWarnings("OptionalGetWithoutIsPresent")

public class GunServiceTest {

    private static GunService service;
    private static ClientService clientService;
    private static OrderService orderService;
    private static Repository<Long, Client> clientRepo;
    private static Repository<Long, Order> orderRepo;
    private static Repository<Long, Gun> gunRepo;
    private static Gun gun1;
    private static Gun gun2;
    private static Order order1;
    private static final Long ID = 1L;
    private static final Long ID2 = 2L;
    private static final Long NEW_ID = 3L;
    private static final String MODEL = "Name1";
    private static final String MODEL2 = "Name2";
    private static final String NEW_MODEL = "Name3";
    private static final String MANUFACTURER = "MANUFACTURER1";
    private static final String MANUFACTURER2 = "MANUFACTURER2";
    private static final String TYPE = "type1";
    private static final String TYPE2 = "type2";
    private static final float WEIGHT = 100;
    private static final float WEIGHT2 = 100;
    private static final float PRICE = 1000;
    private static final float PRICE2 = 2500;
    private static final float NEW_PRICE = 5000;



    @BeforeAll
    static void init()   {
        gun1 = new Gun(ID,MODEL,MANUFACTURER,TYPE,WEIGHT,PRICE);
        gun2 = new Gun(ID2,MODEL2,MANUFACTURER2,TYPE2,WEIGHT2,PRICE2);
        order1 = new Order(1L,1L,1L);
        clientRepo = new InMemoryRepository<>(new ClientValidator());
        orderRepo = new InMemoryRepository<>(new OrderValidator());
        gunRepo = new InMemoryRepository<>(new GunValidator());
        assertDoesNotThrow(()->orderRepo.save(order1), "SHOULD NOT THROW EXCEPTION");
    }

    @BeforeEach
    void setUp() {
        gun1 = new Gun(ID,MODEL,MANUFACTURER,TYPE,WEIGHT,PRICE);
        gun2 = new Gun(ID2,MODEL2,MANUFACTURER2,TYPE2,WEIGHT2,PRICE2);
        service = new GunService(new InMemoryRepository<>(new GunValidator()),orderRepo);
        clientService = new ClientService(new InMemoryRepository<>(new ClientValidator()),orderRepo);
        orderService = new OrderService(new InMemoryRepository<>(new OrderValidator()),service,clientService);
        assertDoesNotThrow(()->service.addGun(gun1), "SHOULD NOT THROW EXCEPTION");
    }

    @org.junit.jupiter.api.Test
    void testAddGun() {
        assertDoesNotThrow(()->service.addGun(gun2), "SHOULD NOT THROW EXCEPTION");
        assertTrue(service.getAllGuns().stream().anyMatch(z->z.equals(gun2)), "THE GUN SHOULD HAVE BEEN ADDED");

    }

    @org.junit.jupiter.api.Test
    void testAddGun2() {
        assertDoesNotThrow(()->service.addGun(gun2), "SHOULD NOT THROW EXCEPTION");
        assertTrue(service.getAllGuns().stream().anyMatch(z->z.equals(gun2)), "THE GUN SHOULD HAVE BEEN ADDED");
    }

    @org.junit.jupiter.api.Test
    void testRemoveGun() {
        assertDoesNotThrow(()->service.delete(gun1.getId()), "SHOULD NOT FAIL");
        assertFalse(service.getAllGuns().stream().anyMatch(v->v.equals(gun1)), "THE GUN SHOULD HAVE BEEN DELETED");
    }

    @org.junit.jupiter.api.Test
    void testUpdateGun(){
        assertDoesNotThrow(()->service.update(gun1), "SHOULD NOT FAIL");
        assertEquals(service.getAllGuns().stream()
                .filter(z->z.getId().equals(gun1.getId()))
                .findFirst().get().getPrice(), gun1.getPrice());
    }

    @org.junit.jupiter.api.Test
    void testExistsGun() {
        assertDoesNotThrow(()->service.addGun(gun2), "SHOULD NOT THROW EXCEPTION");
        assertFalse(service.getAllGuns().stream().anyMatch(z->z.equals(service.existsGun(gun2.getId()))), "THE GUN SHOULD HAVE BEEN ADDED");
    }



    @org.junit.jupiter.api.Test
    void testFilterGunsByModel() {
        StreamSupport.stream(service.filterGunsByModel(MODEL2).spliterator(), false)
                .findAny()
                .ifPresent(z->fail("There is no gun with that model"));
        gun2 = new Gun(ID2,MODEL2,MANUFACTURER2,TYPE2,WEIGHT2,PRICE2);
        assertDoesNotThrow(()->service.addGun(gun2), "SHOULD NOT THROW EXCEPTION");
        assertEquals(StreamSupport.stream(service.filterGunsByModel(MODEL2).spliterator(), false)
                .findFirst().get(), gun2, "FILTER SHOULD RETURN THE GUN WITH MODEL2");
    }

    @org.junit.jupiter.api.Test
    void testFilterGunsByType() {
        StreamSupport.stream(service.filterGunsByType(TYPE2).spliterator(), false)
                .findAny()
                .ifPresent(z->fail("There is no gun with that type"));
        gun2 = new Gun(ID2,MODEL2,MANUFACTURER2,TYPE2,WEIGHT2,PRICE2);
        assertDoesNotThrow(()->service.addGun(gun2), "SHOULD NOT THROW EXCEPTION");
        assertEquals(StreamSupport.stream(service.filterGunsByType(TYPE2).spliterator(), false)
                .findFirst().get(), gun2, "FILTER SHOULD RETURN THE GUN WITH TYPE2");
    }

    @org.junit.jupiter.api.Test
    void testDeleteByGunId() {
        assertDoesNotThrow(()->service.deleteAllOrdersByGunId(gun1.getId()), "SHOULD NOT FAIL");
        assertFalse(StreamSupport.stream(orderService.getAllOrders().spliterator(), false).anyMatch(v->v.equals(gun1.getId())), "THE GUN SHOULD HAVE BEEN DELETED");
    }

    @org.junit.jupiter.api.Test
    void testGetAll() {
        assertEquals(1L, service.getAllGuns().spliterator().getExactSizeIfKnown());
        assertTrue(StreamSupport.stream(service.getAllGuns().spliterator(), false).anyMatch(v->v.equals(gun1)));
    }

    @org.junit.jupiter.api.Test
    void testGetAllSorted() {
        assertEquals(1L, service.getSortedGuns().spliterator().getExactSizeIfKnown());
        assertTrue(StreamSupport.stream(service.getSortedGuns().spliterator(), false).anyMatch(v->v.equals(gun1)));
    }

}
