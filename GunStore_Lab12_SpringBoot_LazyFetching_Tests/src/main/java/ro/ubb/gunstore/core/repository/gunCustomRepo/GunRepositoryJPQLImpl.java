package ro.ubb.gunstore.core.repository.gunCustomRepo;

import ro.ubb.gunstore.core.model.Gun;
import ro.ubb.gunstore.core.repository.CustomRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class GunRepositoryJPQLImpl extends CustomRepositorySupport
        implements GunRepositoryCustom {
    @Override
    public List<Gun> findAllWithOrders() {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(
                "select distinct g from Gun g " +
                        "left join fetch g.gun_orders o " +
                        "left join fetch o.client");
        List<Gun> guns = query.getResultList();
        System.out.println("JPQL");
        return guns;
    }

    @Override
    public Gun findGunWithOrders(Long gunId) {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(
                "select distinct g from Gun g "+
                        "left join fetch g.gun_orders o " +
                        "left join fetch o.client " +
                        "where g.id = ?1");
        query.setParameter(1,gunId);
        var gun = (Gun) query.getResultList().get(0);
        System.out.println("JPQL");
        return gun;
    }
}
