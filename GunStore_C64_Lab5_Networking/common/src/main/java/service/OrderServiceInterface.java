package service;

import domain.Order;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface OrderServiceInterface {
    /**
     * Add an order to the repository.
     * @param order to be added.
     * @throws Exception returns exception if order is not valid
     */
    public CompletableFuture<Optional<Order> > addOrder(Order order) throws Exception;

    /**
     * Delete a given order from the repository.
     * @param aLong the given ID of the order
     * @return optional containing the deleted order, if found
     * @throws Exception if the id is null
     */
    public CompletableFuture<Optional<Order> > delete(Long aLong) throws Exception;

    /**
     * Update a given order in the repository
     * @param entity the order with the updated fields
     * @return optional containing the valid updated order, if found
     * @throws Exception if the new order is invalid
     */
    public CompletableFuture<Optional<Order> > update(Order entity) throws Exception;
    /**
     * Delete all orders containing a given gun ID
     * @param gunId the given gun ID
     */
    public void deleteAllByGunId(Long gunId);
    /**
     * Get all the orders in the repository
     * @return returns all orders from the repository
     */
    public CompletableFuture<Set<Order>> getAllOrders();
}
