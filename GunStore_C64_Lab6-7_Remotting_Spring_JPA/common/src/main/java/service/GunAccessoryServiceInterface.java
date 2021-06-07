package service;

import domain.Gun;
import domain.GunAccessories;
import domain.exceptions.ValidatorException;

import java.util.*;

/**
 * Service interface for handling guns.
 */
public interface GunAccessoryServiceInterface {

    /**
     * Add an accessory to the repository.
     * @param accessories Accessory to be added.
     * @throws ValidatorException returns exception if gun is not valid
     */
    void addAccessory(GunAccessories accessories) throws Exception;

    /**
     * Delete an accessory from the repository with cascade delete for orders.
     * @param aLong the id of the accessory to remove
     * @return the optional containing the deleted accessory, if it was found
     * @throws Exception if the id is null
     */
    GunAccessories deleteAccessory(Long aLong) throws Exception;

    /**
     * Update an accessory from the repository.
     * @param entity the accessory with the updated fields
     * @throws Exception if the accessory is invalid
     */
    void updateAccessory(GunAccessories entity) throws Exception;

    /**
     * Check if an accessory exists in the repository.
     * @param id the ID of the gun to look for
     * @return true or false
     */
    Boolean existsAccessory(Long id);

    /**
     * Get all the accessories in the repository
     * @return returns all accessories from the repository
     */
    List<GunAccessories> getAllAccessories();


}
