package service;

import domain.Gun;
import domain.exceptions.ValidatorException;

import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * Service interface for handling guns.
 */
public interface GunServiceInterface {

    /**
     * Add a gun to the repository.
     * @param gun Gun to be added.
     * @throws ValidatorException returns exception if gun is not valid
     */
    CompletableFuture<Optional<Gun>> addGun(Gun gun) throws Exception;

    /**
     * Delete a gun from the repository with cascade delete for orders.
     * @param aLong the id of the gun to remove
     * @return the optional containing the deleted gun, if it was found
     * @throws Exception if the id is null
     */
    CompletableFuture<Optional<Gun>> delete(Long aLong) throws Exception;

    /**
     * Update a gun from the repository.
     * @param entity the gun with the updated fields
     * @return optional containing the updated gun, if found
     * @throws Exception if the gun is invalid
     */
    CompletableFuture<Optional<Gun>> update(Gun entity) throws Exception;

    /**
     * Check if a gun exists in the repository.
     * @param id the ID of the gun to look for
     * @return true or false
     */
    CompletableFuture<Boolean> existsGun(Long id);

    /**
     * Get all the guns in the repository
     * @return returns all guns from the repository
     */
    CompletableFuture<Set<Gun>> getAllGuns();

    /**
     * Returns all guns whose name contain the given string.
     *
     * @param s String to be contained in the name.
     * @return Set of guns whose name contain a given string.
     */
    CompletableFuture<Set<Gun>> filterGunsByModel(String s);

    /**
     * Returns the guns sorted by price
     * @return List of guns sorted by price
     */
    CompletableFuture<List<Gun>> getSortedGuns();

    /**
     * Get the guns whose type contains a given string
     * @param str the given string
     * @return a set containing the filtered guns
     */
    CompletableFuture<Set<Gun>> filterGunsByType(String str);

    /**
     * Delete all orders containing a given gunId from the repository.
     * @param gunId the given gunId
     */
    void deleteAllOrdersByGunId(Long gunId) ;

    /**
     * Get the top 3 of the most sold guns from the repository.
     * @return a list containing the 3 guns ordered descending by the number of orders they have.
     */
    CompletableFuture<List<Gun>> getTop3SoldGuns();
}
