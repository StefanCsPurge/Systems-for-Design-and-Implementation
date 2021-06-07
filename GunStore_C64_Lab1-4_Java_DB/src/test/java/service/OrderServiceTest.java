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

import java.time.LocalDate;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;
@SuppressWarnings("OptionalGetWithoutIsPresent")

public class OrderServiceTest {

    private static OrderService service;
    private static ClientService clientService;
    private static GunService gunService;
    private static Repository<Long, Client> clientRepo;
    private static Repository<Long, Order> orderRepo;
    private static Repository<Long, Gun> gunRepo;
    private static Order order1;
    private static Order order2;
    private static final Long ID = 1L;
    private static final Long ID2 = 2L;
    private static Client client1;
    private static Client client2;
    private static final Long IDclient1 = 1L;
    private static final Long IDclient2 = 2L;
    private static final Long NEW_ID = 3L;
    private static final String NAME = "Name1";
    private static final String NAME2 = "Name2";
    private static final String NEW_NAME = "Name3";
    private static final String CNP = "6000912312888";
    private static final String CNP2 = "5001023884529";
    private static final float BUDGET = 1000;
    private static final float BUDGET2 = 2500;
    private static final float NEW_BUDGET = 5000;
    private static Gun gun1;
    private static Gun gun2;
    private static final Long IDgun1 = 1L;
    private static final Long IDgun2 = 2L;
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


    @BeforeAll
    static void init()   {
        client1 = new Client(IDclient1,NAME,CNP,BUDGET);
        client2 = new Client(IDclient2,NAME2,CNP2,BUDGET2);
        gun1 = new Gun(IDgun1,MODEL,MANUFACTURER,TYPE,WEIGHT,PRICE);
        gun2 = new Gun(IDgun2,MODEL2,MANUFACTURER2,TYPE2,WEIGHT2,PRICE2);
        order1 = new Order(ID,IDgun1,IDclient1);
        order2 = new Order(ID2,IDgun2,IDclient2);
        clientRepo = new InMemoryRepository<>(new ClientValidator());
        gunRepo = new InMemoryRepository<>(new GunValidator());
        orderRepo = new InMemoryRepository<>(new OrderValidator());
        assertDoesNotThrow(()->clientRepo.save(client1), "SHOULD NOT THROW EXCEPTION");
        assertDoesNotThrow(()->gunRepo.save(gun1), "SHOULD NOT THROW EXCEPTION");
        assertDoesNotThrow(()->clientRepo.save(client2), "SHOULD NOT THROW EXCEPTION");
        assertDoesNotThrow(()->gunRepo.save(gun2), "SHOULD NOT THROW EXCEPTION");
        assertDoesNotThrow(()->orderRepo.save(order1), "SHOULD NOT THROW EXCEPTION");


    }

    @BeforeEach
    void setUp() {
        order1 = new Order(ID,IDgun1,IDclient1);
        order2 = new Order(ID2,IDgun2,IDclient2);
        clientService = new ClientService(new InMemoryRepository<>(new ClientValidator()),orderRepo);
        gunService = new GunService(new InMemoryRepository<>(new GunValidator()),orderRepo);
        service = new OrderService(orderRepo,gunService,clientService);
        assertDoesNotThrow(()->clientService.addClient(client1), "SHOULD NOT THROW EXCEPTION");
        assertDoesNotThrow(()->gunService.addGun(gun1), "SHOULD NOT THROW EXCEPTION");
        assertDoesNotThrow(()->service.addOrder(order1), "SHOULD NOT THROW EXCEPTION");
    }

    @org.junit.jupiter.api.Test
    void testAddOrder() {
        assertDoesNotThrow(()->service.addOrder(order1));
        assertTrue(StreamSupport.stream(service.getAllOrders().spliterator(), false)
                .anyMatch(t->t.equals(order1)), "NEW ORDER SHOULD HAVE BEEN ADDED");
    }

    @org.junit.jupiter.api.Test
    void testRemoveOrder() {
        assertDoesNotThrow(()->service.delete(order1.getId()), "SHOULD NOT FAIL");
        assertFalse(service.getAllOrders().stream().anyMatch(v->v.equals(order1)), "THE ORDER SHOULD HAVE BEEN DELETED");
    }

    @org.junit.jupiter.api.Test
    void testUpdateOrder(){
        assertDoesNotThrow(()->service.update(order1), "SHOULD NOT FAIL");
        assertEquals(service.getAllOrders().stream()
                .filter(z->z.getId().equals(order1.getId()))
                .findFirst().get().getGunId(), order1.getGunId());
    }


    @org.junit.jupiter.api.Test
    void testGetAll() {
        assertEquals(1L, service.getAllOrders().spliterator().getExactSizeIfKnown());
        assertTrue(StreamSupport.stream(service.getAllOrders().spliterator(), false).anyMatch(v->v.equals(order1)));
    }

    @org.junit.jupiter.api.Test
    void testDeleteByGunId() {
        assertDoesNotThrow(()->service.deleteAllByGunId(gun1.getId()), "SHOULD NOT FAIL");
        assertFalse(StreamSupport.stream(service.getAllOrders().spliterator(), false).anyMatch(v->v.equals(gun1.getId())), "THE ORDER SHOULD HAVE BEEN DELETED");
    }


}
