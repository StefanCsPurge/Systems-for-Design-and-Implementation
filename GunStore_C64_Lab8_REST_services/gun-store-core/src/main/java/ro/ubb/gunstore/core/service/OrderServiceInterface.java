package ro.ubb.gunstore.core.service;

import ro.ubb.gunstore.core.model.StoreOrder;

import java.util.List;

public interface OrderServiceInterface {
    /**
     * Add an order to the repository.
     * @param storeOrder to be added.
     * @throws Exception returns exception if order is not valid
     * @return
     */
    StoreOrder addOrder(StoreOrder storeOrder) throws Exception;

    /**
     * Delete a given order from the repository.
     * @param aLong the given ID of the order
     * @return deleted order, if found
     * @throws Exception if the id is null
     */
    StoreOrder delete(Long aLong) throws Exception;

    /**
     * Update a given order in the repository
     * @param entity the order with the updated fields
     * @throws Exception if the new order is invalid
     * @return
     */
    StoreOrder update(StoreOrder entity) throws Exception;

    /**
     * Delete all orders containing a given gun ID
     * @param gunId the given gun ID
     */
    void deleteAllByGunId(Long gunId);

    /**
     * Get all the orders in the repository
     * @return returns all orders from the repository
     */
    List<StoreOrder> getAllOrders();
}
