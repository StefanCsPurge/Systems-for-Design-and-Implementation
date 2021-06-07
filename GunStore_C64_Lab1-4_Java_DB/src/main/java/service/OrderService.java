package service;

import domain.BaseEntity;
import domain.Order;
import domain.validators.ValidatorException;
import repository.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service for handling guns.
 */
public class OrderService {
    private final Repository<Long, Order> orderRepository;
    private final GunService gunService;
    private final ClientService clientService;

    public OrderService(Repository<Long, Order> repository, GunService gunService, ClientService clientService) {
        this.orderRepository = repository;
        this.gunService = gunService;
        this.clientService = clientService;
    }

    /**
     * Add an order to the repository.
     * @param order to be added.
     * @throws ValidatorException returns exception if order is not valid
     */
    public void addOrder(Order order) throws Exception {
        if(gunService.existsGun(order.getGunId()) && clientService.existsClient(order.getClientId()))
            orderRepository.save(order);
        else
            throw new ValidatorException("Non existent gunId/clientId!");
    }

    /**
     * Delete a given order from the repository.
     * @param aLong the given ID of the order
     * @return optional containing the deleted order, if found
     * @throws Exception if the id is null
     */
    public Optional<Order> delete(Long aLong) throws Exception {
        return orderRepository.delete(aLong);
    }

    /**
     * Update a given order in the repository
     * @param entity the order with the updated fields
     * @return optional containing the valid updated order, if found
     * @throws Exception if the new order is invalid
     */
    public Optional<Order> update(Order entity) throws Exception {
        if(gunService.existsGun(entity.getGunId()) && clientService.existsClient(entity.getClientId()))
            return orderRepository.update(entity);
        return Optional.empty();
    }

    /**
     * Delete all orders containing a given gun ID
     * @param gunId the given gun ID
     */
    public void deleteAllByGunId(Long gunId){
        orderRepository.findAll().stream()
                .filter((order) -> order.getGunId().equals(gunId))
                .map((BaseEntity::getId))
                .forEach(id -> {
                    try {
                        orderRepository.delete(id);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    /**
     * Get all the orders in the repository
     * @return returns all orders from the repository
     */
    public Set<Order> getAllOrders() {
        Iterable<Order> orders = orderRepository.findAll();
        return StreamSupport.stream(orders.spliterator(), false).collect(Collectors.toSet());
    }

}
