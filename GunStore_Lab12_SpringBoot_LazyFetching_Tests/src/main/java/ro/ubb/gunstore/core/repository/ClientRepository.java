package ro.ubb.gunstore.core.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import ro.ubb.gunstore.core.model.Client;
import ro.ubb.gunstore.core.repository.clientCustomRepo.ClientRepositoryCustom;

import java.util.List;

public interface ClientRepository extends Repository<Long, Client>, ClientRepositoryCustom {
    @Query("select distinct c from Client c")
    @EntityGraph(value = "clientWithOrders", type = EntityGraph.EntityGraphType.LOAD)
    List<Client> findAllWithOrdersEnt();

    @Query("select distinct c from Client c where c.id = ?1")
    @EntityGraph(value = "clientWithOrders", type = EntityGraph.EntityGraphType.LOAD)
    Client findClientWithOrdersEnt(Long clientId);
}
