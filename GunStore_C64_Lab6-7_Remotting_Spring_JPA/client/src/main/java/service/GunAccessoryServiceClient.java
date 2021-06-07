package service;

import domain.Ammunition;
import domain.GunAccessories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GunAccessoryServiceClient implements GunAccessoryServiceInterface {
    @Qualifier("gunAccessoryServiceServer")
    @Autowired
    private GunAccessoryServiceInterface gunAccessoryService;

    @Override
    public void addAccessory(GunAccessories accessories) throws Exception {
        gunAccessoryService.addAccessory(accessories);
    }

    @Override
    public GunAccessories deleteAccessory(Long ID) throws Exception {
        return gunAccessoryService.deleteAccessory(ID);
    }

    @Override
    public void updateAccessory(GunAccessories accessories) throws Exception {
        gunAccessoryService.updateAccessory(accessories);
    }

    @Override
    public Boolean existsAccessory(Long id) {
        return gunAccessoryService.existsAccessory(id);
    }

    @Override
    public List<GunAccessories> getAllAccessories() {
        return gunAccessoryService.getAllAccessories();
    }
}
