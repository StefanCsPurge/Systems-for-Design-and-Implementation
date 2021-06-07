package repository;

import domain.Gun;
import domain.validators.GunValidator;
import domain.validators.Validator;
import org.junit.Test;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class TxtRepoTest {

    private Validator<Gun> gunValidator = new GunValidator();
    private Repository<Long, Gun> gunRepository = new TxtFileRepository<>(gunValidator, Gun.class , "./data/testGuns");

    private Gun gun1 = new Gun(1L, "model1", "manufacturer1", "type1", 10, 100);

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
}
