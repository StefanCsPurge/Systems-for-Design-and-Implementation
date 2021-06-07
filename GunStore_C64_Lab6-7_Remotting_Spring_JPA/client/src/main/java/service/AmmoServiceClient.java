package service;

import domain.Ammunition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AmmoServiceClient implements AmmoServiceInterface{
    @Qualifier("ammoServiceServer")
    @Autowired
    private AmmoServiceInterface ammoService;

    @Override
    public void addAmmunition(Ammunition ammo) throws Exception {
       ammoService.addAmmunition(ammo);
    }

    @Override
    public Ammunition delete(Long ID) throws Exception {
       return ammoService.delete(ID);
    }

    @Override
    public void update(Ammunition ammo) throws Exception {
        ammoService.update(ammo);
    }

    @Override
    public Boolean existsAmmunition(Long id) {
        return ammoService.existsAmmunition(id);
    }

    @Override
    public List<Ammunition> getAllAmmunition() {
        return ammoService.getAllAmmunition();
    }
}
