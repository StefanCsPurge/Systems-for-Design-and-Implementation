package service;

import domain.Gun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class GunServiceClient implements GunServiceInterface{
    @Qualifier("gunServiceServer")
    @Autowired
    private GunServiceInterface gunService;

    @Override
    public void addGun(Gun gun) throws Exception {
        gunService.addGun(gun);
    }

    @Override
    public Gun deleteGun(Long ID) throws Exception {
        return gunService.deleteGun(ID);
    }

    @Override
    public void updateGun(Gun gun) throws Exception {
        gunService.updateGun(gun);
    }

    @Override
    public Boolean existsGun(Long id) {
        return gunService.existsGun(id);
    }

    @Override
    public List<Gun> getAllGuns() {
        /*try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return gunService.getAllGuns();
    }

    @Override
    public Set<Gun> filterGunsByModel(String s) {
        return gunService.filterGunsByModel(s);
    }

    @Override
    public Set<Gun> filterGunsByType(String s) {
        return gunService.filterGunsByType(s);
    }

    @Override
    public List<Gun> getSortedGuns() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return gunService.getSortedGuns();
    }

    @Override
    public List<Gun> getTop3SoldGuns() {
        return gunService.getTop3SoldGuns();
    }

    @Override
    public void deleteAllOrdersByGunId(Long gunId) {}
}
