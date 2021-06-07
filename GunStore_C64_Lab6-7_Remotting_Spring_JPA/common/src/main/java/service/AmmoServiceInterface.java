package service;
import domain.Ammunition;

import java.util.List;

public interface AmmoServiceInterface {
    void addAmmunition(Ammunition entity) throws Exception;

    Ammunition delete(Long aLong) throws Exception;

    void update(Ammunition entity) throws Exception;

    Boolean existsAmmunition(Long id);

    List<Ammunition> getAllAmmunition();
}
