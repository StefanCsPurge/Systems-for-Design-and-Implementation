package ro.ubb.gunstore.core.repository.clientCustomRepo;

import ro.ubb.gunstore.core.model.Client;

import java.util.List;

public interface ClientRepositoryCustom {
    List<Client> findAllWithOrders();

    Client findClientWithOrders(Long clientId);
}
