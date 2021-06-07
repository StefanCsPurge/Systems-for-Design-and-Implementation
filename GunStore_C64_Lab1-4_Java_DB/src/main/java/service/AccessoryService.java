package service;

import domain.GunAccessories;
import domain.validators.ValidatorException;
import repository.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class AccessoryService {
    private final Repository<Long, GunAccessories> repo;
    public AccessoryService (Repository<Long, GunAccessories> repository) {this.repo = repository;}

    /**
     *
     * @param accessory The new accessory to be added
     * @throws ValidatorException  returns exception if the accessory is not valid
     */
    public void addAccessory(GunAccessories accessory) throws Exception {
        repo.save(accessory);
    }

    /**
     *
     * @param accessory the accessory which we want to delete
     * @return Deletes the accessory
     */
    public Optional<GunAccessories> delete(Long accessory) throws Exception {
        return repo.delete(accessory);
    }

    /**
     *
     * @param accessory the accessory which we want to update
     * @return The updated accessory
     * @throws Exception in case the update fails
     */
    public Optional<GunAccessories> update(GunAccessories accessory) throws Exception {
        return repo.update(accessory);
    }

    /**
     *  Get all the accessories from repo
     * @return returns all the accessories
     */
    public Set<GunAccessories> getAllAccessories(){
        Iterable<GunAccessories> accessories = repo.findAll();
        return StreamSupport.stream(accessories.spliterator(), false).collect(Collectors.toSet());
    }

    /**
     * Returns all the gun accessories whose name contain the given string.
     *
     * @param s String to be contained in the name.
     * @return Set of accessories whose company contain a given string.
     */
    public Set<GunAccessories> filterByCompany(String s) {
        Iterable<GunAccessories> accessories = repo.findAll();

        Set<GunAccessories> filteredAccessories= new HashSet<>();
        accessories.forEach(filteredAccessories::add);
        filteredAccessories.removeIf(accessory -> !accessory.getCompany().contains(s));

        return filteredAccessories;
    }


    /**
     * Get the gun accessories with the price between 2 values
     * @param min the lower bound for price
     * @param max the upper bound for price
     * @return the set of filtered accessories
     */
    public Set<GunAccessories> filterByPrice(int min, int max) {
        Iterable<GunAccessories> accessories = repo.findAll();

        Set<GunAccessories> filteredAccessories= new HashSet<>();
        accessories.forEach(filteredAccessories::add);
        filteredAccessories.removeIf(accessory -> accessory.getPrice()< min || accessory.getPrice()> max);
        return filteredAccessories;
    }
}
