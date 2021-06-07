package ro.ubb.gunstore.core.repository.gunCustomRepo;

import ro.ubb.gunstore.core.model.*;
import ro.ubb.gunstore.core.repository.CustomRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;

public class GunRepositoryCriteriaImpl extends CustomRepositorySupport
                                        implements GunRepositoryCustom{
    @Override
    public List<Gun> findAllWithOrders() {
        EntityManager entityManager = getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Gun> query = criteriaBuilder.createQuery(Gun.class);
        query.distinct(Boolean.TRUE);
        Root<Gun> root = query.from(Gun.class);
//        query.select(root);
        Fetch<Gun, StoreOrder> gunOrderFetch = root.fetch(Gun_.gun_orders, JoinType.LEFT);
        gunOrderFetch.fetch(StoreOrder_.client, JoinType.LEFT);

        List<Gun> guns = entityManager.createQuery(query).getResultList();
        System.out.println("Criteria API");
        return guns;
    }

    @Override
    public Gun findGunWithOrders(Long gunId) {
        EntityManager entityManager = getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Gun> query = criteriaBuilder.createQuery(Gun.class);
        query.distinct(Boolean.TRUE);

        Root<Gun> root = query.from(Gun.class);
        ParameterExpression<Long> pe = criteriaBuilder.parameter(Long.class);
        query.where(criteriaBuilder.equal(root.get("id"), pe));

//        query.select(root);
        Fetch<Gun, StoreOrder> gunOrderFetch = root.fetch(Gun_.gun_orders, JoinType.LEFT);
        gunOrderFetch.fetch(StoreOrder_.client, JoinType.LEFT);

        var gunQuery = entityManager.createQuery(query);
        gunQuery.setParameter(pe, gunId);

        var gun = gunQuery.getResultList().get(0);
        System.out.println("Criteria API");
        return gun;
    }
}
