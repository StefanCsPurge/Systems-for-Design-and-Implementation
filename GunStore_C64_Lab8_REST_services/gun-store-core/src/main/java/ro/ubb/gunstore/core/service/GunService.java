package ro.ubb.gunstore.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.gunstore.core.model.BaseEntity;
import ro.ubb.gunstore.core.model.Gun;
import ro.ubb.gunstore.core.model.exceptions.RepositoryException;
import ro.ubb.gunstore.core.model.validators.GunValidator;
import ro.ubb.gunstore.core.repository.GunRepository;
import ro.ubb.gunstore.core.repository.OrderRepository;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service for handling guns.
 */
@Service
public class GunService implements GunServiceInterface{
    private static final Logger log = LoggerFactory.getLogger(GunService.class);

    @Autowired
    private GunRepository gunRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private GunValidator gunValidator;

    /**
     * Add a gun to the repository.
     * @param gun Gun to be added.
     */
    @Override
    public Gun addGun(Gun gun) {
        log.trace("addGun - method entered: gun={}", gun);
        gunValidator.validate(gun);
        var result = gunRepository.save(gun);
        log.trace("addGun - result={}", result);
        return result;
    }

    /**
     * Delete a gun from the repository with cascade delete for orders.
     * @param id the id of the gun to remove
     * @return the optional containing the deleted gun, if it was found
     */
    @Override
    public Gun deleteGun(Long id) {
        // throw new RuntimeException("not yet implemented");
        log.trace("deleteGun - method entered: gunID={}", id);
        this.deleteAllOrdersByGunId(id);
        var opt = gunRepository.findById(id);
        if(opt.isEmpty())
            throw new RepositoryException("Gun with the id " + id + " is not in the repository.");
        gunRepository.deleteById(id);
        log.trace("deleteGun: result={}",opt.get());
        return opt.get();
    }

    /**
     * Update a gun from the repository.
     * @param gun the gun with the updated fields
     */
    @Override
    public Gun updateGun(Gun gun) {
        log.trace("updateGun - method entered: gun={}", gun);
        gunValidator.validate(gun);
        var result = gunRepository.save(gun);
        log.debug("updateGun - updated: gun={}", gun);
        log.trace("updateGun - result={}", result);
        return result;
    }

    /**
     * Check if a gun exists in the repository.
     * @param id the ID of the gun to look for
     * @return true or false
     */
    @Override
    public Boolean existsGun(Long id) {
        log.trace("existsGun - method entered: gunID={}", id);
        var result =  gunRepository.findById(id).isPresent();
        log.trace("existsGun: result={}", result);
        return result;
    }

    /**
     * Get all the guns in the repository
     * @return returns all guns from the repository
     */
    @Override
    public List<Gun> getAllGuns() {
        log.trace("getAllGuns - method entered");
        var result =  gunRepository.findAll();
        log.trace("getAllGuns: result={}",result);
        return result;
    }

    /**
     * Returns all guns whose name contain the given string.
     * @param filterStr String to be contained in the name.
     * @return Set of guns whose name contain a given string.
     */
    @Override
    public Set<Gun> filterGunsByModel(String filterStr) {
        log.trace("filterGunsByModel - method entered: filterStr={}", filterStr);
        var result = gunRepository.findByModelIgnoreCase(filterStr);
        log.trace("filterGunsByModel: result={}", result);
        return result;
    }

    /**
     * Returns the guns sorted by price
     * @return List of guns sorted by price
     */
    @Override
    public List<Gun> getSortedGuns() {
        log.trace("getSortedGuns - method entered");
        try {
            Thread.sleep(4000);  // simulate sorting taking long to complete
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        var list = gunRepository.findByOrderByPriceAsc();
        log.trace("getSortedGuns: result={}", list);
        return list;
    }

    /**
     * Get the guns whose type contains a given string
     * @param filterStr the given string
     * @return a set containing the filtered guns
     */
    @Override
    public Set<Gun> filterGunsByType(String filterStr) {
        log.trace("filterGunsByType - method entered: filterStr={}", filterStr);
        var result = gunRepository.findByTypeIgnoreCaseContains(filterStr);
        log.trace("filterGunsByType: result={}", result);
        return result;
    }

    /**
     * Delete all orders containing a given gunId from the repository.
     * @param gunId the given gunId
     */
    @Override
    public void deleteAllOrdersByGunId(Long gunId) {
        log.trace("deleteAllOrdersByGunId - method entered");
        orderRepository.findAll().stream()
                .filter((order) -> order.getGunId().equals(gunId))
                .map((BaseEntity::getId))
                .forEach(id -> {
                    try {
                        orderRepository.deleteById(id);
                    } catch (Exception e) {
                        throw new RuntimeException(e.getCause());
                    }
                });
        log.trace("deleteAllOrdersByGunId - method finished");
    }

    /**
     * Get the top 3 of the most sold guns from the repository.
     * @return a list containing the 3 guns ordered descending by the number of orders they have.
     */
    @Override
    public List<Gun> getTop3SoldGuns(){
        log.trace("getTop3SoldGuns - method entered");
        var orders = orderRepository.findAll();

        Map<Gun, Long> gunsNOrders = new HashMap<>();
        gunRepository.findAll().forEach(gun -> gunsNOrders.put(gun,
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
            topGun.setTimesSold(noOfSales);
            topGuns.add(topGun);
            gunsNOrders.remove(topGun);
        });
        log.trace("getTop3SoldGuns: result={}", topGuns);
        return topGuns;
    }

}
