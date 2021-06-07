package ro.ubb.gunstore.core.service;

import ro.ubb.gunstore.core.model.StoreOrder;

import java.util.List;

public interface OrderServiceInterface {
    /**
     * Add an order to the repository.
     * @param storeOrder to be added.
     * @throws Exception returns exception if order is not valid
     */
    StoreOrder addOrder(StoreOrder storeOrder) throws Exception;

    /**
     * Delete a given order from the repository.
     * @param clientId, orderID : the given IDs
     * @throws Exception if any ID does not exist in the repository
     */
    StoreOrder deleteClientOrder(Long clientId, Long orderId) throws Exception;

    /**
     * Update a given order in the repository
     * @param updatedOrder the order with the updated fields
     * @throws Exception if the new order is invalid
     * @return the updated StoreOrder
     */
    StoreOrder updateClientOrder(StoreOrder updatedOrder) throws Exception;

    /**
     * Delete all orders containing a given gun ID
     * @param gunId the given gun ID
     */
    void deleteAllByGunId(Long gunId);

    /**
     * Get all the orders for a client
     * @return returns all needed orders from the repository
     */
    List<StoreOrder> getAllClientOrders(Long clientId);

    /**
     * Get all the orders for a gun
     * @return returns all needed orders from the repository
     */
    List<StoreOrder> getAllGunOrders(Long gunId);
}
