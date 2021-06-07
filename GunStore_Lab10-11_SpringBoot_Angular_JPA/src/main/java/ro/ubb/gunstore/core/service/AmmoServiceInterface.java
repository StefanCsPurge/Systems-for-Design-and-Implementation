package ro.ubb.gunstore.core.service;

import ro.ubb.gunstore.core.model.Ammunition;

import java.util.List;

public interface AmmoServiceInterface {
    Ammunition addAmmunition(Ammunition entity) throws Exception;

    Ammunition delete(Long aLong) throws Exception;

    Ammunition update(Ammunition entity) throws Exception;

    Boolean existsAmmunition(Long id);

    List<Ammunition> getAllAmmunition();
}
