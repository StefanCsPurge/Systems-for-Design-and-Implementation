package repository;

import domain.*;
import domain.validators.*;
import org.junit.AfterClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runners.model.TestClass;
import repository.TxtFileRepository;
import repository.Repository;
import repository.JDBCRepository;
import service.AmmoService;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

public class JDBCRepositoryTest {
        private static String CONNECTION_STRING = "jdbc::postgresql://localhost:5432/";
        final static private String TABLE_NAME = "test_table";
        final static private String DATABASE_NAME = "test_repo_db";
        static private String username;
        static private String password;
        private static List<TestClass> testData;
        private static JDBCRepository<Long, Client> repository;


//    @BeforeAll
//    void setUp() {
//        username = System.getProperty("username");
//        password = System.getProperty("property");
//        testData = new ArrayList<>();
//        String statement = "CREATE DATABASE %s".formatted(DATABASE_NAME);
//        String createTable = "CREATE TABLE %S (".formatted(TABLE_NAME)+
//                "id INT PRIMARY KEY, " +
//                "name VARCHAR(255)" +
//                "time TIMESTAMP, " +
//                "value INT " +
//                ")";
//
//        try(var connection = DriverManager.getConnection(CONNECTION_STRING, username, password)){
//            connection.createStatement().executeUpdate(statement);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        try(var connection = DriverManager.getConnection(CONNECTION_STRING + DATABASE_NAME, username, password)){
//            connection.createStatement().executeUpdate(createTable);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        repository = new JDBCRepository<>(new ClientValidator(), Client.class, "db_credentials"); {
//        }
//    }
//    @AfterClass
//    public static void tearDown() throws SQLException{
//        String statement = "DROP DATABASE %s".formatted(DATABASE_NAME);
//        try (var connection = DriverManager.getConnection(CONNECTION_STRING, username, password)){
//            connection.createStatement().executeUpdate(statement);
//        }
//    }

    @Test
    public void testAddUpdateDeletClient() {
          JDBCRepository<Long, Client> repository = new JDBCRepository<>(new ClientValidator(), Client.class, "db_credentials");
        Client cli1 = new Client(9999L, "Marius", "1111111111111", 500);
        assertDoesNotThrow(()->repository.save(cli1), "SHOULD NOT THROW EXCEPTION");

        var entity = StreamSupport.stream(repository.findOne(9999L).stream().spliterator(), false).findFirst();
        assertTrue(entity.isPresent());
        assertEquals(entity.get(), cli1);

        assertDoesNotThrow(()->repository.update(cli1), "SHOULD NOT THROW EXCEPTION");
        assertTrue(StreamSupport.stream(repository.findOne(9999L).stream().spliterator(), false)
                .filter(a->a.getId().equals(9999L))
                .anyMatch(a->a.getName().equals("Marius")));

        assertDoesNotThrow(()->repository.delete(9999L), "SHOULD NOT THROW EXCEPTION");
        assertFalse(StreamSupport.stream(repository.findOne(9999L).stream().spliterator(), false)
                .anyMatch(a -> a.getId().equals(9999L)));
    }
    @Test
    public void testAddUpdateDeletGun() {
        JDBCRepository<Long, Gun> repository = new JDBCRepository<>(new GunValidator(), Gun.class, "db_credentials");
        Gun gun1 = new Gun(9999L,"ak47","america","semi",500,500);
        assertDoesNotThrow(()->repository.save(gun1), "SHOULD NOT THROW EXCEPTION");

        var entity = StreamSupport.stream(repository.findOne(9999L).stream().spliterator(), false).findFirst();
        assertTrue(entity.isPresent());
        assertEquals(entity.get(), gun1);

        assertDoesNotThrow(()->repository.update(gun1), "SHOULD NOT THROW EXCEPTION");
        assertTrue(StreamSupport.stream(repository.findOne(9999L).stream().spliterator(), false)
                .filter(a->a.getId().equals(9999L))
                .anyMatch(a->a.getManufacturer().equals("america")));

        assertDoesNotThrow(()->repository.delete(9999L), "SHOULD NOT THROW EXCEPTION");
        assertFalse(StreamSupport.stream(repository.findOne(9999L).stream().spliterator(), false)
                .anyMatch(a -> a.getId().equals(9999L)));
    }
    @Test
    public void testAddUpdateDeletOrder() {
        JDBCRepository<Long, Order> repository = new JDBCRepository<>(new OrderValidator(), Order.class, "db_credentials");
        Order order1 = new Order(9999L,9999L,9999L);
        assertDoesNotThrow(()->repository.save(order1), "SHOULD NOT THROW EXCEPTION");

        var entity = StreamSupport.stream(repository.findOne(9999L).stream().spliterator(), false).findFirst();
        assertTrue(entity.isPresent());
        assertEquals(entity.get(), order1);

        assertDoesNotThrow(()->repository.update(order1), "SHOULD NOT THROW EXCEPTION");
        assertTrue(StreamSupport.stream(repository.findOne(9999L).stream().spliterator(), false)
                .filter(a->a.getId().equals(9999L))
                .anyMatch(a->a.getClientId().equals(9999L)));

        assertDoesNotThrow(()->repository.delete(9999L), "SHOULD NOT THROW EXCEPTION");
        assertFalse(StreamSupport.stream(repository.findOne(9999L).stream().spliterator(), false)
                .anyMatch(a -> a.getId().equals(9999L)));
    }
    @Test
    public void testAddUpdateDeletAmmo() {
        JDBCRepository<Long, Ammunition> repository = new JDBCRepository<>(new AmmoValidator(), Ammunition.class, "db_credentials");
        Ammunition ammo1 = new Ammunition(9999L,100,100,"america",100);
        assertDoesNotThrow(()->repository.save(ammo1), "SHOULD NOT THROW EXCEPTION");

        var entity = StreamSupport.stream(repository.findOne(9999L).stream().spliterator(), false).findFirst();
        assertTrue(entity.isPresent());
        assertEquals(entity.get(), ammo1);

        assertDoesNotThrow(()->repository.update(ammo1), "SHOULD NOT THROW EXCEPTION");
        assertTrue(StreamSupport.stream(repository.findOne(9999L).stream().spliterator(), false)
                .filter(a->a.getId().equals(9999L))
                .anyMatch(a->a.getManufacturer().equals("america")));

        assertDoesNotThrow(()->repository.delete(9999L), "SHOULD NOT THROW EXCEPTION");
        assertFalse(StreamSupport.stream(repository.findOne(9999L).stream().spliterator(), false)
                .anyMatch(a -> a.getId().equals(9999L)));
    }
    @Test
    public void testAddUpdateDeletAccessory() {
        JDBCRepository<Long, GunAccessories> repository = new JDBCRepository<>(new AccessoryValidator(), GunAccessories.class, "db_credentials");
        GunAccessories accessories = new GunAccessories(9999L,"husa","mana","america",100);
        assertDoesNotThrow(()->repository.save(accessories), "SHOULD NOT THROW EXCEPTION");

        var entity = StreamSupport.stream(repository.findOne(9999L).stream().spliterator(), false).findFirst();
        assertTrue(entity.isPresent());
        assertEquals(entity.get(), accessories);

        assertDoesNotThrow(()->repository.update(accessories), "SHOULD NOT THROW EXCEPTION");
        assertTrue(StreamSupport.stream(repository.findOne(9999L).stream().spliterator(), false)
                .filter(a->a.getId().equals(9999L))
                .anyMatch(a->a.getName().equals("husa")));

        assertDoesNotThrow(()->repository.delete(9999L), "SHOULD NOT THROW EXCEPTION");
        assertFalse(StreamSupport.stream(repository.findOne(9999L).stream().spliterator(), false)
                .anyMatch(a -> a.getId().equals(9999L)));
    }
}
