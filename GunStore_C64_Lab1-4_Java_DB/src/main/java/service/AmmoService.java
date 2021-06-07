package service;

import domain.Ammunition;
import domain.validators.ValidatorException;
import repository.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class AmmoService {
    private final Repository<Long, Ammunition> repo;
    public AmmoService (Repository<Long, Ammunition> repository) {this.repo = repository;}

    /**
     *
     * @param ammo The new ammo to be added
     * @throws ValidatorException  returns exception if the accessory is not valid
     */
    public void addAmmo(Ammunition ammo) throws Exception {
        repo.save(ammo);
    }

    /**
     *
     * @param ammoID the ID of the ammo we want to delete
     * @return Deletes the ammo
     */
    public Optional<Ammunition> delete(Long ammoID) throws Exception {
        return repo.delete(ammoID);
    }

    /**
     *
     * @param ammo the ammo we want to update
     * @return The updated ammo
     * @throws Exception in case the update fails
     */
    public Optional<Ammunition> update(Ammunition ammo) throws Exception {
        return repo.update(ammo);
    }

    /**
     *  Get all the accessories from repo
     * @return returns all the accessories
     */
    public Set<Ammunition> getAllAmmo(){
        Iterable<Ammunition> ammo = repo.findAll();
        return StreamSupport.stream(ammo.spliterator(), false).collect(Collectors.toSet());
    }
}
