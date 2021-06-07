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

public class ClientServiceTest {

    private static ClientService service;
    private static GunService gunService;
    private static OrderService orderService;
    private static Repository<Long, Client> clientRepo;
    private static Repository<Long, Order> orderRepo;
    private static Repository<Long, Gun> gunRepo;
    private static Client client1;
    private static Client client2;
    private static Order order1;
    private static final Long ID = 1L;
    private static final Long ID2 = 2L;
    private static final Long NEW_ID = 3L;
    private static final String NAME = "Name1";
    private static final String NAME2 = "Name2";
    private static final String NEW_NAME = "Name3";
    private static final String CNP = "6000912312888";
    private static final String CNP2 = "5001023884529";
    private static final float BUDGET = 1000;
    private static final float BUDGET2 = 2500;
    private static final float NEW_BUDGET = 5000;


    @BeforeAll
    static void init()   {
        client1 = new Client(ID,NAME,CNP,BUDGET);
        client2 = new Client(ID2,NAME2,CNP2,BUDGET2);
        order1 = new Order(1L,1L,1L);
        clientRepo = new InMemoryRepository<>(new ClientValidator());
        orderRepo = new InMemoryRepository<>(new OrderValidator());
        gunRepo = new InMemoryRepository<>(new GunValidator());
        assertDoesNotThrow(()->orderRepo.save(order1), "SHOULD NOT THROW EXCEPTION");
    }

    @BeforeEach
    void setUp() {
        client1 = new Client(ID,NAME,CNP,BUDGET);
        client2 = new Client(ID2,NAME2,CNP2,BUDGET2);
        service = new ClientService(new InMemoryRepository<>(new ClientValidator()),orderRepo);
        gunService = new GunService(new InMemoryRepository<>(new GunValidator()),orderRepo);
        orderService = new OrderService(new InMemoryRepository<>(new OrderValidator()),gunService,service);
        assertDoesNotThrow(()->service.addClient(client1), "SHOULD NOT THROW EXCEPTION");
    }

    @org.junit.jupiter.api.Test
    void testAddClient() {
        assertDoesNotThrow(()->service.addClient(client2), "SHOULD NOT THROW EXCEPTION");
        assertTrue(service.getAllClients().stream().anyMatch(z->z.equals(client2)), "THE CLIENT SHOULD HAVE BEEN ADDED");

    }

    @org.junit.jupiter.api.Test
    void testAddClient2() {
        assertDoesNotThrow(()->service.addClient(client2), "SHOULD NOT THROW EXCEPTION");
        assertTrue(service.getAllClients().stream().anyMatch(z->z.equals(client2)), "THE CLIENT SHOULD HAVE BEEN ADDED");
    }

    @org.junit.jupiter.api.Test
    void testRemoveClient() {
        assertDoesNotThrow(()->service.delete(client1.getId()), "SHOULD NOT FAIL");
        assertFalse(service.getAllClients().stream().anyMatch(v->v.equals(client1)), "THE CLIENT SHOULD HAVE BEEN DELETED");
    }

    @org.junit.jupiter.api.Test
    void testUpdateClient(){
        assertDoesNotThrow(()->service.update(client1), "SHOULD NOT FAIL");
        assertEquals(service.getAllClients().stream()
                .filter(z->z.getId().equals(client1.getId()))
                .findFirst().get().getBudget(), client1.getBudget());
    }
    @org.junit.jupiter.api.Test
    void testExistsClient() {
        assertDoesNotThrow(()->service.addClient(client2), "SHOULD NOT THROW EXCEPTION");
        assertFalse(service.getAllClients().stream().anyMatch(z->z.equals(service.existsClient(client2.getId()))), "THE CLIENT SHOULD HAVE BEEN ADDED");
    }



    @org.junit.jupiter.api.Test
    void testFilterAccessoriesByCompany() {
        StreamSupport.stream(service.filterClientsByName(NEW_NAME).spliterator(), false)
                .findAny()
                .ifPresent(z->fail("There is no client with that name"));
        client2 = new Client(ID2,NAME2,CNP2,BUDGET2);
        assertDoesNotThrow(()->service.addClient(client2), "SHOULD NOT THROW EXCEPTION");
        assertEquals(StreamSupport.stream(service.filterClientsByName(NAME2).spliterator(), false)
                .findFirst().get(), client2, "FILTER SHOULD RETURN THE CLIENT WITH NAME2");
    }
    @org.junit.jupiter.api.Test
    void testDeleteByClientId() {
        assertDoesNotThrow(()->service.deleteAllOrdersByClientId(client1.getId()), "SHOULD NOT FAIL");
        assertFalse(StreamSupport.stream(orderService.getAllOrders().spliterator(), false).anyMatch(v->v.equals(client1.getId())), "THE CLIENT SHOULD HAVE BEEN DELETED");
    }

    @org.junit.jupiter.api.Test
    void testGetAll() {
        assertEquals(1L, service.getAllClients().spliterator().getExactSizeIfKnown());
        assertTrue(StreamSupport.stream(service.getAllClients().spliterator(), false).anyMatch(v->v.equals(client1)));
    }

}
