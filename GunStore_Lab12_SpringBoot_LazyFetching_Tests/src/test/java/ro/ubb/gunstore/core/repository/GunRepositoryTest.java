package ro.ubb.gunstore.core.repository;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
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
import ro.ubb.gunstore.core.model.Gun;

import java.util.List;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ITConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@DatabaseSetup("classpath:db-data.xml")
public class GunRepositoryTest {
    @Autowired
    private GunRepository gunRepository;

    @Test
    public void testFindAllWithOrders(){
        List<Gun> guns = gunRepository.findAllWithOrders();
        var orders1 = guns.get(0).getGun_orders();
        assertEquals("Gun 1 should have no purchase", orders1.size(), 0);
        var orders2 = guns.get(1).getGun_orders();
        assertEquals("Gun 2 should have no purchase", orders2.size(), 0);
    }

    @Test
    public void testFindByTypeIgnoreCaseContains(){
        var foundGuns = gunRepository.findByTypeIgnoreCaseContains("type");
        assertEquals("there should be 2 guns", foundGuns.size(), 2);
        foundGuns = gunRepository.findByTypeIgnoreCaseContains("type1");
        assertEquals("there should be 1 gun", foundGuns.size(), 1);
    }

    @Test
    public void testFindGunWithAccessories(){
        var gun = gunRepository.findGunWithAccessories(11L);
        var accessories = gun.getGunAccessories();
        assertEquals("Gun should have no accessory", accessories.size(), 0);
    }
}
