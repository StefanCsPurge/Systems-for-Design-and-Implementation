package ro.ubb.gunstore.core.repository.gunCustomRepo;

import ro.ubb.gunstore.core.model.Gun;

import java.util.List;

public interface GunRepositoryCustom {

    List<Gun> findAllWithOrders();

    Gun findGunWithOrders(Long gunId);

}
