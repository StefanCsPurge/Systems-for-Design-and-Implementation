package ro.ubb.gunstore.core.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import ro.ubb.gunstore.core.model.Gun;
import ro.ubb.gunstore.core.repository.gunCustomRepo.GunRepositoryCustom;

import java.util.List;
import java.util.Set;

public interface GunRepository extends Repository<Long, Gun>, GunRepositoryCustom {

    Set<Gun> findByModelIgnoreCase(String model);

    Set<Gun> findByTypeIgnoreCaseContains(String typeStr);

    List<Gun> findByOrderByPriceAsc();

    @Query("select distinct g from Gun g")
    @EntityGraph(value = "gunWithAccessories", type = EntityGraph.EntityGraphType.LOAD)
    List<Gun> findAllWithAccessories();

    @Query("select distinct g from Gun g")
    @EntityGraph(value = "gunWithOrders", type = EntityGraph.EntityGraphType.LOAD)
    List<Gun> findAllWithOrdersEnt();

    @Query("select distinct g from Gun g where g.id = ?1")
    @EntityGraph(value = "gunWithOrders", type = EntityGraph.EntityGraphType.LOAD)
    Gun findGunWithOrdersEnt(Long gunId);

    @Query("select distinct g from Gun g where g.id = ?1")
    @EntityGraph(value = "gunWithAccessories", type = EntityGraph.EntityGraphType.LOAD)
    Gun findGunWithAccessories(Long gunId);
}
