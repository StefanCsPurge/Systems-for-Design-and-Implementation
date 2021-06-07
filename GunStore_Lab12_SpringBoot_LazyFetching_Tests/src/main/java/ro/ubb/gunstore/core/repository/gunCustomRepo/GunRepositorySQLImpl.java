package ro.ubb.gunstore.core.repository.gunCustomRepo;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManager;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.gunstore.core.model.Gun;
import ro.ubb.gunstore.core.repository.CustomRepositorySupport;

import java.util.List;

public class GunRepositorySQLImpl extends CustomRepositorySupport implements GunRepositoryCustom{
    @Override
    @Transactional
    public List<Gun> findAllWithOrders() {
        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();

        org.hibernate.Query query = session.createSQLQuery("select distinct {g.*},{o.*},{c.*} " +
                "from gun g " +
                "left join store_order o on g.id=o.gun_id " +
                "left join client c on o.client_id=c.id ")
                .addEntity("g", Gun.class)
                .addJoin("o", "g.gun_orders")
                .addJoin("c", "o.client")
                .addEntity("g",Gun.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Gun> guns = query.getResultList();
        System.out.println("NativeSQL");
        return guns;
    }

    @Override
    @Transactional
    public Gun findGunWithOrders(Long gunId) {
        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();

        org.hibernate.Query query = session.createSQLQuery("select distinct {g.*},{o.*},{c.*} " +
                "from gun g " +
                "left join store_order o on g.id=o.gun_id " +
                "left join client c on o.client_id=c.id " +
                "where g.id=" + gunId)
                .addEntity("g", Gun.class)
                .addJoin("o", "g.gun_orders")
                .addJoin("c", "o.client")
                .addEntity("g", Gun.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Gun> guns = query.getResultList();
        System.out.println("NativeSQL");
        return guns.get(0);
    }
}
