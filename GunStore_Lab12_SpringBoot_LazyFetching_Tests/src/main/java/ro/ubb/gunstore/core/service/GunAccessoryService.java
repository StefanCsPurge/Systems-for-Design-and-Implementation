package ro.ubb.gunstore.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.gunstore.core.model.GunAccessories;
import ro.ubb.gunstore.core.model.validators.AccessoryValidator;
import ro.ubb.gunstore.core.repository.GunRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for handling gun accessories.
 */
@Service
public class GunAccessoryService implements GunAccessoryServiceInterface {
    private static final Logger log = LoggerFactory.getLogger(GunAccessoryService.class);

    @Autowired
    private GunRepository gunRepository;

    @Autowired
    private AccessoryValidator accessoryValidator;

    /**
     * Add an accessory to the repository.
     * @param accessory Accessory to be added.
     */
    @Override
    public void addAccessory(GunAccessories accessory) {
        log.trace("addAccessory - method entered: gunAccessory={}", accessory);
        accessoryValidator.validate(accessory);
        var gun = accessory.getGun();
        gun.getGunAccessories().add(accessory);

        gunRepository.save(gun);
        log.trace("addAccessory - method finished");
    }

    /**
     * Delete an accessory from the repository.
     * @param id the ID of the accessory to remove
     */
    @Override
    public void deleteAccessory(Long id) {
        log.trace("deleteAccessory - method entered: gunAccessoryID={}", id);
        this.getAllAccessories().stream().filter(accessory -> accessory.getId().equals(id)).collect(Collectors.toList())
                .forEach(accessory ->{
                    var gun = accessory.getGun();
                    gun.getGunAccessories().remove(accessory);
                    gunRepository.save(gun);
                });
        log.trace("deleteGunAccessory - method finished");
    }

    /**
     * Update an accessory from the repository.
     * @param updatedAccessory the accessory with the updated fields
     */
    @Override
    public void updateAccessory(GunAccessories updatedAccessory) {
        log.trace("updateGunAccessory - method entered: updatedGunAccessory={}", updatedAccessory);
        accessoryValidator.validate(updatedAccessory);
        updatedAccessory.getGun().getGunAccessories()
                .removeIf(accessory -> accessory.getId().equals(updatedAccessory.getId()));
        updatedAccessory.getGun().getGunAccessories().add(updatedAccessory);
        gunRepository.save(updatedAccessory.getGun());
        log.debug("updateGunAccessory - updated: gunAccessory={}", updatedAccessory);
        log.trace("updateGunAccessory - method finished");
    }

    /**
     * Check if an accessory exists in the repository.
     * @param id the ID of the accessory to look for
     * @return true or false
     */
    @Override
    public Boolean existsAccessory(Long id) {
        log.trace("existsAccessory - method entered: gunAccessoryID={}", id);
        var result = this.getAllAccessories().stream().anyMatch(acc -> acc.getId().equals(id));
        log.trace("existsAccessory: result={}", result);
        return result;
    }

    /**
     * Get all the accessories in the repository
     * @return returns all accessories from the repository
     */
    @Override
    public List<GunAccessories> getAllAccessories() {
        log.trace("getAllAccessories - method entered");
        List<GunAccessories> gunAccessories = new ArrayList<>();
        gunRepository.findAllWithAccessories().forEach(gun ->
                gunAccessories.addAll(gun.getGunAccessories()));
        log.trace("getAllAccessories: result={}",gunAccessories);
        return gunAccessories;
    }


}
