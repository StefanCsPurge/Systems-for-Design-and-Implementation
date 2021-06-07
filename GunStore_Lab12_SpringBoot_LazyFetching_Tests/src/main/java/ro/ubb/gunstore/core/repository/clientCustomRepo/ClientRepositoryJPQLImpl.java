package ro.ubb.gunstore.core.repository.clientCustomRepo;

import ro.ubb.gunstore.core.model.Client;
import ro.ubb.gunstore.core.repository.CustomRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ClientRepositoryJPQLImpl extends CustomRepositorySupport
                                      implements ClientRepositoryCustom {
    @Override
    public List<Client> findAllWithOrders() {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(
                "select distinct c from Client c " +
                        "left join fetch c.client_orders o " +
                        "left join fetch o.ordered_gun");
        List<Client> clients = query.getResultList();
        System.out.println("JPQL");
        return clients;

        //setParameter
        //... where b.publisher.name = :pname
        //... where b.publisher.name = :?1
        //query.setParameter("pname","p1");
        //query.setParameter(1,"p1");
    }

    @Override
    public Client findClientWithOrders(Long clientId) {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(
                "select distinct c from Client c "+
                        "left join fetch c.client_orders o " +
                        "left join fetch o.ordered_gun " +
                        "where c.id = ?1");
        query.setParameter(1,clientId);
        var client = (Client) query.getResultList().get(0);
        System.out.println("JPQL");
        return client;
    }
}
