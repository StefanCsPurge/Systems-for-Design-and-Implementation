package service;

import domain.BaseEntity;
import domain.Gun;
import domain.Order;
import domain.exceptions.ValidatorException;
import repository.Repository;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

/**
 * Service for handling guns.
 */
public class GunService implements GunServiceInterface{
    private final ExecutorService executor;
    private final Repository<Long, Gun> repository;
    private final Repository<Long, Order> orderRepository;

    public GunService(Repository<Long, Gun> repository, Repository<Long,Order> orderRepository, ExecutorService executorService) {
        this.repository = repository;
        this.orderRepository = orderRepository;
        this.executor = executorService;
    }

    /**
     * Add a gun to the repository.
     * @param gun Gun to be added.
     * @throws ValidatorException returns exception if gun is not valid
     * @return null
     */
    @Override
    public CompletableFuture<Optional<Gun>> addGun(Gun gun) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return repository.save(gun);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        });
    }

    /**
     * Delete a gun from the repository with cascade delete for orders.
     * @param aLong the id of the gun to remove
     * @return the optional containing the deleted gun, if it was found
     * @throws RuntimeException if the id is null
     */
    @Override
    public CompletableFuture<Optional<Gun>> delete(Long aLong){
        return CompletableFuture.supplyAsync(()-> {
            try {
                this.deleteAllOrdersByGunId(aLong);
                return repository.delete(aLong);
            } catch (Exception e) {
                throw new RuntimeException(e.getCause());
            }
        });
    }

    /**
     * Update a gun from the repository.
     * @param entity the gun with the updated fields
     * @return optional containing the updated gun, if found
     * @throws RuntimeException if the gun is invalid
     */
    @Override
    public CompletableFuture<Optional<Gun>> update(Gun entity) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return repository.update(entity);
            } catch (Exception e) {
                throw new RuntimeException(e.getCause());
            }
        });
    }

    /**
     * Check if a gun exists in the repository.
     * @param id the ID of the gun to look for
     * @return true or false
     */
    @Override
    public CompletableFuture<Boolean> existsGun(Long id) {
        return CompletableFuture.supplyAsync(() ->
                repository.findOne(id).isPresent());
    }

    /**
     * Get all the guns in the repository
     * @return returns all guns from the repository
     */
    @Override
    public CompletableFuture<Set<Gun>> getAllGuns() {
        return CompletableFuture.supplyAsync(() ->
                new HashSet<>(repository.findAll())
        );
    }

    /**
     * Returns all guns whose name contain the given string.
     *
     * @param s String to be contained in the name.
     * @return Set of guns whose name contain a given string.
     */
    @Override
    public CompletableFuture<Set<Gun>> filterGunsByModel(String s) {
        return CompletableFuture.supplyAsync(() -> repository.findAll().stream()
                .filter(gun -> gun.getModel().contains(s))
                .collect(Collectors.toSet()));
    }

    /**
     * Returns the guns sorted by price
     * @return List of guns sorted by price
     */
    @Override
    public CompletableFuture<List<Gun>> getSortedGuns() {
        return CompletableFuture.supplyAsync(() -> {
            Iterable<Gun> guns = repository.findAll();
            ArrayList<Gun> list = new ArrayList<>();
            guns.forEach(list::add);
            list.sort(Comparator.comparing(Gun::getPrice));
            return list;
        });
    }

    /**
     * Get the guns whose type contains a given string
     * @param str the given string
     * @return a set containing the filtered guns
     */
    @Override
    public CompletableFuture<Set<Gun>> filterGunsByType(String str) {
        return CompletableFuture.supplyAsync(() -> repository.findAll().stream()
                .filter(gun -> gun.getType().contains(str))
                .collect(Collectors.toSet()));
    }

    /**
     * Delete all orders containing a given gunId from the repository.
     * @param gunId the given gunId
     */
    @Override
    public void deleteAllOrdersByGunId(Long gunId) {
        orderRepository.findAll().stream()
                .filter((order) -> order.getGunId().equals(gunId))
                .map((BaseEntity::getId))
                .forEach(id -> {
                    try {
                        orderRepository.delete(id);
                    } catch (Exception e) {
                        throw new RuntimeException(e.getCause());
                    }
                });
    }

    /**
     * Get the top 3 of the most sold guns from the repository.
     * @return a list containing the 3 guns ordered descending by the number of orders they have.
     */
    @Override
    public CompletableFuture<List<Gun>> getTop3SoldGuns(){
        return CompletableFuture
                .supplyAsync(orderRepository::findAll, this.executor)
                .thenApply(orders ->
        {
            Map<Gun, Long> gunsNOrders = new HashMap<>();
            repository.findAll().forEach(gun -> gunsNOrders.put(gun,
                    orders.stream()
                            .filter((order) -> order.getGunId().equals(gun.getId()))
                            .count()));
            List<Long> sortedValues = new ArrayList<>(gunsNOrders.values());
            sortedValues.sort(Collections.reverseOrder());
            List<Long> top3 = sortedValues.stream().limit(3).collect(Collectors.toList());

            List<Gun> topGuns = new ArrayList<>();
            top3.forEach(noOfSales -> {
                Gun topGun = gunsNOrders.entrySet().stream()
                        .filter(entry -> noOfSales.equals(entry.getValue()))
                        .map(Map.Entry::getKey).collect(Collectors.toList()).get(0);
                topGuns.add(topGun);
                gunsNOrders.remove(topGun);
            });
            return topGuns;
        });
    }
}
