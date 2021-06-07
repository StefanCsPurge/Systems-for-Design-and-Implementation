package ro.ubb.gunstore.core.service;

import conf.ITConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import ro.ubb.gunstore.core.model.Gun;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ITConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@DatabaseSetup("classpath:db-data.xml")
public class GunServiceTest {
    @Autowired
    private GunService gunService;

    @Test
    public void findAll() {
        List<Gun> guns = gunService.getAllGuns();
        assertEquals("there should be 2 guns", 2, guns.size());
    }

    @Test
    public void updateGun() {
        var updatedGun = Gun.builder()
                .model("newModel")
                .type("type2")
                .manufacturer("manufacturer2")
                .price(22)
                .weight(22)
                .build();
        updatedGun.setId(11L);
        gunService.updateGun(updatedGun);
        assertEquals("The model should be the new one", gunService.getGun(11L).getModel(), "newModel");
    }

    @Test
    public void addGun() {
        var newGun = Gun.builder()
                .model("model420")
                .type("type420")
                .manufacturer("manufacturer420")
                .price(420)
                .weight(420)
                .timesSold(420)
                .build();
        System.out.println("\n" + newGun + "\n");
        gunService.addGun(newGun);
        assertEquals("there should be 3 guns", 3, gunService.getAllGuns().size());
    }

    @Test
    public void deleteGun() {
        gunService.deleteGun(22L);
        assertEquals("there should now be 1 gun", 1, gunService.getAllGuns().size());
    }
}
