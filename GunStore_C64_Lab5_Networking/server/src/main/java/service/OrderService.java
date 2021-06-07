package service;

import domain.BaseEntity;
import domain.Order;

import java.util.*;
import java.util.concurrent.CompletableFuture;

import repository.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutorService;


public class OrderService implements OrderServiceInterface {
    private final Repository<Long, Order> orderRepository;
    private final GunService gunService;
    private final ClientService clientService;
    private final ExecutorService executorService;

    public OrderService(Repository<Long, Order> repository, GunService gunService, ClientService clientService, ExecutorService executorService) {
        this.orderRepository = repository;
        this.gunService = gunService;
        this.clientService = clientService;
        this.executorService = executorService;
    }

    @Override
    public CompletableFuture<Optional<Order>> addOrder(Order order) throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            try {
                if(gunService.existsGun(order.getGunId()).get() && clientService.existsClient(order.getClientId()).get())
                    return orderRepository.save(order);
                else
                    throw new Exception("Non existent gunId/clientId!");
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        });
    }

    @Override
    public CompletableFuture<Optional<Order>> delete(Long aLong) throws Exception {
        return CompletableFuture.supplyAsync(()-> {
            try {
                return orderRepository.delete(aLong);
            } catch (Exception e) {
                throw new RuntimeException(e.getCause());
            }
        });
    }

    @Override
    public CompletableFuture<Optional<Order>> update(Order entity) throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            try {
                if(gunService.existsGun(entity.getGunId()).get() && clientService.existsClient(entity.getClientId()).get())
                    return orderRepository.update(entity);
                throw new Exception("non existant gunid/clientid");
            } catch (Exception e) {
                throw new RuntimeException(e.getCause());
            }
        });
    }

    @Override
    public void deleteAllByGunId(Long gunId) {
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

    @Override
    public CompletableFuture<Set<Order>> getAllOrders() {
        return CompletableFuture.supplyAsync(() ->
                new HashSet<>(orderRepository.findAll())
        );
    }
}
