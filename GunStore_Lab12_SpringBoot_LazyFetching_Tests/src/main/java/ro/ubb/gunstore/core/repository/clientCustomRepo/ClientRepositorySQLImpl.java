package ro.ubb.gunstore.core.repository.clientCustomRepo;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManager;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.gunstore.core.model.Client;
import ro.ubb.gunstore.core.repository.CustomRepositorySupport;

import java.util.List;

public class ClientRepositorySQLImpl extends CustomRepositorySupport implements ClientRepositoryCustom{
    @Override
    @Transactional
    public List<Client> findAllWithOrders() {
        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();

        org.hibernate.Query query = session.createSQLQuery("select distinct {c.*},{o.*},{g.*} " +
                "from client c " +
                "left join store_order o on c.id=o.client_id " +
                "left join gun g on o.gun_id=g.id ")
                .addEntity("c",Client.class)
                .addJoin("o", "c.client_orders")
                .addJoin("g", "o.ordered_gun")
                .addEntity("c",Client.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Client> clients = query.getResultList();
        System.out.println("NativeSQL");
        return clients;
    }

    @Override
    @Transactional
    public Client findClientWithOrders(Long clientId) {
        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();

        org.hibernate.Query query = session.createSQLQuery("select distinct {c.*},{o.*},{g.*} " +
                "from client c " +
                "left join store_order o on c.id=o.client_id " +
                "left join gun g on o.gun_id=g.id " +
                "where c.id=" + clientId)
                .addEntity("c",Client.class)
                .addJoin("o", "c.client_orders")
                .addJoin("g", "o.ordered_gun")
                .addEntity("c",Client.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Client> clients = query.getResultList();
        System.out.println("NativeSQL");
        return clients.get(0);
    }
}
