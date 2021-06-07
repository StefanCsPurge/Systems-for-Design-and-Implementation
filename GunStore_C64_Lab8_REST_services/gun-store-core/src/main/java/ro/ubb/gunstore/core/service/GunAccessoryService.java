package ro.ubb.gunstore.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.gunstore.core.model.GunAccessories;
import ro.ubb.gunstore.core.model.exceptions.RepositoryException;
import ro.ubb.gunstore.core.model.validators.AccessoryValidator;
import ro.ubb.gunstore.core.repository.GunAccessoryRepository;


import java.util.*;

/**
 * Service for handling gun accessories.
 */
@Service
public class GunAccessoryService implements GunAccessoryServiceInterface {
    private static final Logger log = LoggerFactory.getLogger(GunAccessoryService.class);

    @Autowired
    private GunAccessoryRepository gunAccessoryRepository;
    @Autowired
    private AccessoryValidator accessoryValidator;

    /**
     * Add an accessory to the repository.
     * @param accessories Accessory to be added.
     */
    @Override
    public GunAccessories addAccessory(GunAccessories accessories) {
        log.trace("addAccessory - method entered: gunAccessory={}", accessories);
        accessoryValidator.validate(accessories);
        var result = gunAccessoryRepository.save(accessories);
        log.trace("addAccessory - result={}", result);
        return result;
    }

    /**
     * Delete an accessory from the repository.
     * @param id the id of the accessory to remove
     * @return the optional containing the deleted accessory, if it was found
     */
    @Override
    public GunAccessories deleteAccessory(Long id) {
        log.trace("deleteAccessory - method entered: gunAccessoryID={}", id);
        var opt = gunAccessoryRepository.findById(id);
        if(opt.isEmpty())
            throw new RepositoryException("GunAccessory with the id " + id + " is not in the repository.");
        gunAccessoryRepository.deleteById(id);
        log.trace("deleteGunAccessory: result={}",opt.get());
        return opt.get();
    }

    /**
     * Update an accessory from the repository.
     * @param accessory the accessory with the updated fields
     */
    @Override
    public GunAccessories updateAccessory(GunAccessories accessory) {
        log.trace("updateGunAccessory - method entered: gunAccessory={}", accessory);
        accessoryValidator.validate(accessory);
        var result = gunAccessoryRepository.save(accessory);
        log.debug("updateGunAccessory - updated: gunAccessory={}", accessory);
        log.trace("updateGunAccessory - result={}", result);
        return result;
    }

    /**
     * Check if an accessory exists in the repository.
     * @param id the ID of the accessory to look for
     * @return true or false
     */
    @Override
    public Boolean existsAccessory(Long id) {
        log.trace("existsAccessory - method entered: gunAccessoryID={}", id);
        var result =  gunAccessoryRepository.findById(id).isPresent();
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
        var result =  gunAccessoryRepository.findAll();
        log.trace("getAllAccessories: result={}",result);
        return result;
    }


}
