package service;

import domain.BaseEntity;
import domain.Client;
import domain.Order;
import domain.validators.ValidatorException;
import repository.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service for handling clients.
 */
public class ClientService {
    private final Repository<Long, Client> repository;
    private final Repository<Long, Order> orderRepository;

    public ClientService(Repository<Long, Client> repository, Repository<Long, Order> orderRepository) {
        this.repository = repository;
        this.orderRepository = orderRepository;
    }

    /**
     * Add a client to the repository.
     * @param client Client to be added.
     * @throws ValidatorException returns exception if client is not valid*/
    public void addClient(Client client) throws Exception {
        repository.save(client);
    }

    /**
     * Delete a client from the repository.
     * @param aLong id of the client to be removed
     * @return an optional containing the client that was deleted, in case it was found
     * @throws Exception if the id is null
     */
    public Optional<Client> delete(Long aLong) throws Exception {
        this.deleteAllOrdersByClientId(aLong);
        return repository.delete(aLong);
    }

    /**
     * Update a client in the repository
     * @param entity the client with the updated fields
     * @return optional containing the updated client, in case it was found
     * @throws Exception in case of invalid client
     */
    public Optional<Client> update(Client entity) throws Exception {
        return repository.update(entity);
    }

    /**
     * Check if a client exists in the repository
     * @param id the ID of the client we are looking for
     * @return true or false
     */
    public boolean existsClient(Long id) {
        Optional<Client> client = repository.findOne(id);
        return client.isPresent();
    }

    /**
     * Get all the clients in the repository
     * @return returns all clients from the repository
     */
    public Set<Client> getAllClients() {
        Iterable<Client> clients = repository.findAll();
        return StreamSupport.stream(clients.spliterator(), false).collect(Collectors.toSet());
    }

    /**
     * Returns all clients whose name contain the given string.
     *
     * @param s String to be contained in the name.
     * @return Set of clients whose name contain a given string.
     */
    public Set<Client> filterClientsByName(String s) {
        Iterable<Client> clients = repository.findAll();
        Set<Client> filteredClients= new HashSet<>();
        clients.forEach(filteredClients::add);
        filteredClients.removeIf(client -> !client.getName().contains(s));
        return filteredClients;
    }

    /**
     * Removes all orders with a given clientId from the repository
     * @param clientId the given id
     */
    public void deleteAllOrdersByClientId(Long clientId){
        orderRepository.findAll().stream()
                .filter((order) -> order.getClientId().equals(clientId))
                .map((BaseEntity::getId))
                .forEach(id -> {
                    try {
                        orderRepository.delete(id);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
