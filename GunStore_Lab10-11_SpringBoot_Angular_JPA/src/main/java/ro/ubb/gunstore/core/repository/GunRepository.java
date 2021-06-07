package ro.ubb.gunstore.core.repository;

import ro.ubb.gunstore.core.model.Gun;

import java.util.List;
import java.util.Set;

public interface GunRepository extends Repository<Long, Gun>{

    Set<Gun> findByModelIgnoreCase(String model);

    Set<Gun> findByTypeIgnoreCaseContains(String typeStr);

    List<Gun> findByOrderByPriceAsc();
}
