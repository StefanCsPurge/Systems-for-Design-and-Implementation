package repository;

import domain.*;
import domain.validators.*;
import jdk.jshell.spi.ExecutionControlProvider;
import org.junit.Ignore;
import org.junit.Test;
import repository.InMemoryRepository;
import repository.Repository;
import repository.Repository;

import java.util.HashSet;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class InMemoryRepoTest {

    //Adding all the needed repositories
    private Validator<Gun> gunValidator = new GunValidator();
    private Repository<Long, Gun> gun_repository = new InMemoryRepository<Long, Gun>(gunValidator);
    private Validator<Order> orderValidator = new OrderValidator();
    private Repository<Long, Order> order_repository = new InMemoryRepository<Long, Order>(orderValidator);
    private Validator<Client> clientValidator = new ClientValidator();
    private Repository<Long, Client> client_repository = new InMemoryRepository<Long, Client>(clientValidator);
    private Validator<GunAccessories> accessoriesValidator = new AccessoryValidator();
    private Repository<Long, GunAccessories> accessories_repository = new InMemoryRepository<Long, GunAccessories>(accessoriesValidator);
    private Validator<Ammunition> ammoValidator = new AmmoValidator();
    private Repository<Long, Ammunition> ammo_repository = new InMemoryRepository<Long, Ammunition>(ammoValidator);

    //Adding GUNS
    private Gun gun1 = new Gun(1L, "model1", "manufacturer1", "type1", 10, 100);
    private final Gun gun_error1 = new Gun(-2L, "model3", "manufacturer3", "type3", 100, 100);
    private Gun gun_error2 = new Gun(3L, "model3", "manufacturer3", "type3",-1 , 100);
    private Gun gun_error3 = new Gun(4L, "model3", "manufacturer3", "type3", 100, -1);

    //Adding ORDERS
    private Order order_error1 = new Order(-1L,1L,1L);
    private Order order_error2 = new Order(1L,-1L,1L);
    private Order order_error3 = new Order(1L,1L,-1L);

    //Adding CLIENTS
    private Client client_error1 = new Client(1L,"testName","",999);
    private Client client_error2 = new Client(1L,"testName","1234567890123",-1);

    //Adding ACCESSORIES
    private GunAccessories accessory_error1 = new GunAccessories(-1L,"testName","testType","testCompany",99);
    private GunAccessories accessory_error2 = new GunAccessories(1L,"testName","testType","testCompany",-1);

    //Adding AMMO
    private Ammunition ammunition_error1 = new Ammunition(-1L,100,100,"testManufacturer",99);
    private Ammunition ammunition_error2 = new Ammunition(1L,-100,100,"testManufacturer",99);
    private Ammunition ammunition_error3 = new Ammunition(1L,100,-100,"testManufacturer",99);
    private Ammunition ammunition_error4 = new Ammunition(1L,100,100,"testManufacturer",-1);

    @org.junit.jupiter.api.Test
    public void testFindOne() throws Exception {
        gun_repository.save(gun1);
        Optional<Gun> added_gun =  gun_repository.findOne(1L);
        assertEquals(added_gun, gun_repository.findOne(1L));
    }

    @org.junit.jupiter.api.Test
    public void testFindOneError() throws Exception {
        try {
            Optional<Gun> added_gun = gun_repository.findOne(null);
        } catch(Exception e) {
        }
    }

    @org.junit.jupiter.api.Test
    public void testFindAll() throws Exception {
        Iterable<Gun> guns = gun_repository.findAll();
        assertEquals(gun_repository.findAll(), guns);
    }

    @org.junit.jupiter.api.Test
    public void testSaveErrorGuns() throws Exception {
        try {
            gun_repository.save(gun_error1);
        } catch(Exception ignored) {}
        try {
            gun_repository.save(gun_error2);
        } catch(Exception ignored) {}
        try {
            gun_repository.save(gun_error3);
        } catch(Exception ignored) {}
        try {
            gun_repository.save(null);
        } catch(Exception ignored) {}
    }

    @org.junit.jupiter.api.Test
    public void testSaveErrorOrders() throws Exception {
        try {
            order_repository.save(order_error1);
        } catch(Exception ignored) {}
        try {
            order_repository.save(order_error2);
        } catch(Exception ignored) {}
        try {
            order_repository.save(order_error3);
        } catch(Exception ignored) {}
        try {
            order_repository.save(null);
        } catch(Exception ignored) {}
    }

    @org.junit.jupiter.api.Test
    public void testSaveErrorClients() throws Exception {
        try {
            client_repository.save(client_error1);
        } catch(Exception ignored) {}
        try {
            client_repository.save(client_error2);
        } catch(Exception ignored) {}
        try {
            client_repository.save(null);
        } catch(Exception ignored) {}
    }

    @org.junit.jupiter.api.Test
    public void testSaveErrorAccessories() throws Exception {
        try {
            accessories_repository.save(accessory_error1);
        } catch(Exception ignored) {}
        try {
            accessories_repository.save(accessory_error2);
        } catch(Exception ignored) {}
        try {
            accessories_repository.save(null);
        } catch(Exception ignored) {}
    }

    @org.junit.jupiter.api.Test
    public void testSaveErrorAmmo() throws Exception {
        try {
            ammo_repository.save(ammunition_error1);
        } catch(Exception ignored) {}
        try {
            ammo_repository.save(ammunition_error2);
        } catch(Exception ignored) {}
        try {
            ammo_repository.save(ammunition_error3);
        } catch(Exception ignored) {}
        try {
            ammo_repository.save(ammunition_error4);
        } catch(Exception ignored) {}
        try {
            ammo_repository.save(null);
        } catch(Exception ignored) {}
    }

    @org.junit.jupiter.api.Test
    public void testDelete() throws Exception {
        try {
            gun_repository.delete(null);
        } catch(Exception ignored) {}

        gun_repository.delete(1L);
        int guns = (int) gun_repository.findAll().spliterator().getExactSizeIfKnown();
        assertEquals(guns , 0);
    }

    @org.junit.jupiter.api.Test
    public void testUpdate() throws Exception {
        gun_repository.save(gun1);
        Gun gun_update = new Gun(1L, "modelTest", "manufacturerTest", "typeTest", 99, 999);
        try {
            gun_repository.update(null);
        } catch(Exception ignored) {}

        gun_repository.update(gun_update);
        Optional<Gun> added_gun =  gun_repository.findOne(1L);
        assertEquals(added_gun, gun_repository.findOne(1L));

    }
}