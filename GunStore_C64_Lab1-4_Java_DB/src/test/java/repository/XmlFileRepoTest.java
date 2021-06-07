package repository;

import domain.*;
import domain.validators.*;
import org.junit.Test;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class XmlFileRepoTest {

    private Validator<Gun> gunValidator = new GunValidator();
    private Repository<Long, Gun> gunRepository = new XmlFileRepository<>(gunValidator, Gun.class , "./data/testGuns.xml");
    private Validator<Order> orderValidator = new OrderValidator();
    private Repository<Long, Order> orderRepository = new XmlFileRepository<>(orderValidator, Order.class, "./data/testOrders.xml");
    private Validator<Client> clientValidator = new ClientValidator();
    private Repository<Long, Client> clientRepository = new XmlFileRepository<>(clientValidator, Client.class, "./data/testClients.xml");
    private Validator<GunAccessories> accessoriesValidator = new AccessoryValidator();
    private Repository<Long, GunAccessories> accessoriesRepository = new XmlFileRepository<>(accessoriesValidator, GunAccessories.class, "./data/testAccessories.xml");
    private Validator<Ammunition> ammoValidator = new AmmoValidator();
    private Repository<Long, Ammunition> ammoRepository = new XmlFileRepository<>(ammoValidator, Ammunition.class , "./data/testAmmo.xml");

    private Gun gun1 = new Gun(1L, "model1", "manufacturer1", "type1", 10, 100);
    private Order order = new Order(1L,1L,1L);
    private Client client = new Client(1L,"testName","1234567890123",999);
    private GunAccessories accessory = new GunAccessories(1L,"testName","testType","testCompany",99);
    private Ammunition ammunition = new Ammunition(1L,100,100,"testManufacturer",99);

    @org.junit.jupiter.api.Test
    public void testFindOne() throws Exception {
        gunRepository.save(gun1);
        Optional<Gun> added_gun =  gunRepository.findOne(1L);
        assertEquals(added_gun, gunRepository.findOne(1L));
    }

    @org.junit.jupiter.api.Test
    public void testFindOneError() throws Exception {
        try {
            Optional<Gun> added_gun = gunRepository.findOne(null);
        } catch(Exception e) {
        }
    }

    @org.junit.jupiter.api.Test
    public void testFindAll() throws Exception {
        Iterable<Gun> guns = gunRepository.findAll();
        assertEquals(gunRepository.findAll(), guns);
    }

    @org.junit.jupiter.api.Test
    public void testDelete() throws Exception {
        try {
            gunRepository.delete(null);
        } catch(Exception ignored) {}

        gunRepository.delete(1L);
        int guns = (int) gunRepository.findAll().spliterator().getExactSizeIfKnown();
        assertEquals(guns , 0);
    }

    @org.junit.jupiter.api.Test
    public void testUpdate() throws Exception {
        gunRepository.save(gun1);
        Gun gun_update = new Gun(1L, "modelTest", "manufacturerTest", "typeTest", 99, 999);
        try {
            gunRepository.update(null);
        } catch(Exception ignored) {}

        gunRepository.update(gun_update);
        Optional<Gun> added_gun =  gunRepository.findOne(1L);
        assertEquals(added_gun, gunRepository.findOne(1L));

    }

    @org.junit.jupiter.api.Test
    public void testFindOneForDomain() throws Exception {
        orderRepository.save(order);
        clientRepository.save(client);
        accessoriesRepository.save(accessory);
        ammoRepository.save(ammunition);

        Optional<Order> added_order =  orderRepository.findOne(1L);
        Optional<Client> added_client =  clientRepository.findOne(1L);
        Optional<GunAccessories> added_accessory =  accessoriesRepository.findOne(1L);
        Optional<Ammunition> added_ammo =  ammoRepository.findOne(1L);

    }

}
