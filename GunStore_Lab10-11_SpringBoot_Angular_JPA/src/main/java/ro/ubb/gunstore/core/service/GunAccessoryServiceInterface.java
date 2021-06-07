package ro.ubb.gunstore.core.service;

import ro.ubb.gunstore.core.model.GunAccessories;

import java.util.List;

/**
 * Service interface for handling guns.
 */
public interface GunAccessoryServiceInterface {

    /**
     * Add an accessory to the repository.
     * @param accessory Accessory to be added.
     * @throws Exception returns exception if entity is not valid */
    void addAccessory(GunAccessories accessory) throws Exception;

    /**
     * Delete an accessory from the repository with cascade delete for orders.
     * @param id the id of the accessory to remove
     * @throws Exception if the id is null */
    void deleteAccessory(Long id) throws Exception;

    /**
     * Update an accessory from the repository.
     * @param entity the accessory with the updated fields
     * @throws Exception if the accessory is invalid */
    void updateAccessory(GunAccessories entity) throws Exception;

    /**
     * Check if an accessory exists in the repository.
     * @param id the ID of the gun to look for
     * @return true or false */
    Boolean existsAccessory(Long id);

    /**
     * Get all the accessories in the repository
     * @return returns all accessories from the repository */
    List<GunAccessories> getAllAccessories();

}
