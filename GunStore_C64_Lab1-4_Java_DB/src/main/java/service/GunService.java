package service;

import domain.BaseEntity;
import domain.Gun;
import domain.Order;
import domain.validators.ValidatorException;
import repository.Repository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service for handling guns.
 */
public class GunService {
    private final Repository<Long, Gun> repository;
    private final Repository<Long, Order> orderRepository;

    public GunService(Repository<Long, Gun> repository, Repository<Long,Order> orderRepository) {
        this.repository = repository;
        this.orderRepository = orderRepository;
    }

    /**
     * Add a gun to the repository.
     * @param gun Gun to be added.
     * @throws ValidatorException returns exception if gun is not valid
     */
    public void addGun(Gun gun) throws Exception {
        repository.save(gun);
    }

    /**
     * Delete a gun from the repository with cascade delete for orders.
     * @param aLong the id of the gun to remove
     * @return the optional containing the deleted gun, if it was found
     * @throws Exception if the id is null
     */
    public Optional<Gun> delete(Long aLong) throws Exception{
        this.deleteAllOrdersByGunId(aLong);
        return repository.delete(aLong);
    }

    /**
     * Update a gun from the repository.
     * @param entity the gun with the updated fields
     * @return optional containing the updated gun, if found
     * @throws Exception if the gun is invalid
     */
    public Optional<Gun> update(Gun entity) throws Exception {
        return repository.update(entity);
    }

    /**
     * Check if a gun exists in the repository.
     * @param id the ID of the gun to look for
     * @return true or false
     */
    public boolean existsGun(Long id) {
        Optional<Gun> gun = repository.findOne(id);
        return gun.isPresent();
    }

    /**
     * Get all the guns in the repository
     * @return returns all guns from the repository
     */
    public Set<Gun> getAllGuns() {
        Iterable<Gun> guns = repository.findAll();
        return StreamSupport.stream(guns.spliterator(), false).collect(Collectors.toSet());
    }

    /**
     * Returns all guns whose name contain the given string.
     *
     * @param s String to be contained in the name.
     * @return Set of guns whose name contain a given string.
     */
    public Set<Gun> filterGunsByModel(String s) {
        Iterable<Gun> guns = repository.findAll();

        Set<Gun> filteredGuns= new HashSet<>();
        guns.forEach(filteredGuns::add);
        filteredGuns.removeIf(gun -> !gun.getModel().contains(s));

        return filteredGuns;
    }

    /**
     * Returns the guns sorted by price
     * @return List of guns sorted by price
     */
    public List<Gun> getSortedGuns() {
        Iterable<Gun> guns = repository.findAll();
        ArrayList<Gun> list = new ArrayList<>();
        guns.forEach(list::add);

        list.sort(Comparator.comparing(Gun::getPrice));
        return list;
    }

    /**
     * Get the guns whose type contains a given string
     * @param str the given string
     * @return a set containing the filtered guns
     */
    public Set<Gun> filterGunsByType(String str) {
        Iterable<Gun> guns = repository.findAll();

        Set<Gun> filteredGuns = new HashSet<>();
        guns.forEach(filteredGuns::add);
        filteredGuns.removeIf(gun -> !gun.getType().contains(str));
        return filteredGuns;
    }

    /**
     * Delete all orders containing a given gunId from the repository.
     * @param gunId the given gunId
     */
    public void deleteAllOrdersByGunId(Long gunId) {
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
     * Get the top 3 of the most sold guns from the repository.
     * @return a list containing the 3 guns ordered descending by the number of orders they have.
     */
    public List<Gun> getTop3SoldGuns(){
        Map<Gun, Long> gunsNOrders = new HashMap<>();
        repository.findAll().forEach(gun -> gunsNOrders.put(gun,
                        orderRepository.findAll().stream()
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
    }
}
