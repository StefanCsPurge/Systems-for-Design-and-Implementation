package ro.ubb.gunstore.core.repository.clientCustomRepo;

import ro.ubb.gunstore.core.model.Client;
import ro.ubb.gunstore.core.model.Client_;
import ro.ubb.gunstore.core.model.StoreOrder;
import ro.ubb.gunstore.core.model.StoreOrder_;
import ro.ubb.gunstore.core.repository.CustomRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;

public class ClientRepositoryCriteriaImpl extends CustomRepositorySupport implements ClientRepositoryCustom{
    @Override
    public List<Client> findAllWithOrders() {
        EntityManager entityManager = getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Client> query = criteriaBuilder.createQuery(Client.class);
        query.distinct(Boolean.TRUE);
        Root<Client> root = query.from(Client.class);
//        query.select(root);
        Fetch<Client, StoreOrder> clientOrderFetch = root.fetch(Client_.client_orders, JoinType.LEFT);
        clientOrderFetch.fetch(StoreOrder_.ordered_gun, JoinType.LEFT);

        List<Client> clients = entityManager.createQuery(query).getResultList();
        System.out.println("Criteria API");
        return clients;

        /*
        where b.publisher.name = :?1
        query.setParameter(1,"p1");

        ParameterExpression<String> pe=criteriaBuilder.parameter(String.class);
        query.where(criteriaBuilder.equal(pe,"p1"));
         */
    }

    @Override
    public Client findClientWithOrders(Long clientId) {
        EntityManager entityManager = getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Client> query = criteriaBuilder.createQuery(Client.class);
        query.distinct(Boolean.TRUE);

        Root<Client> root = query.from(Client.class);
        ParameterExpression<Long> pe = criteriaBuilder.parameter(Long.class);
        query.where(criteriaBuilder.equal(root.get("id"), pe));

//        query.select(root);
        Fetch<Client, StoreOrder> clientOrderFetch = root.fetch(Client_.client_orders, JoinType.LEFT);
        clientOrderFetch.fetch(StoreOrder_.ordered_gun, JoinType.LEFT);

        var clientQuery = entityManager.createQuery(query);
        clientQuery.setParameter(pe, clientId);

        var client = clientQuery.getResultList().get(0);
        System.out.println("Criteria API");
        return client;
    }
}
