package service;

import domain.BaseEntity;
import domain.Client;
import domain.Order;

import repository.Repository;
import domain.exceptions.ValidatorException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service for handling clients.
 */
public class ClientService implements ClientServiceInterface {
    private final Repository<Long, Client> repository;
    private final Repository<Long, Order> orderRepository;
    private final ExecutorService executorService;

    public ClientService(Repository<Long, Client> repository, Repository<Long, Order> orderRepository, ExecutorService executorService) {
        this.repository = repository;
        this.orderRepository = orderRepository;
        this.executorService = executorService;
    }

    /**
     * Add a client to the repository.
     * @param client Client to be added.
     * @throws ValidatorException returns exception if client is not valid*/

    public CompletableFuture<Optional<Client> > addClient(Client client) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return repository.save(client);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        });
    }

    /**
     * Delete a client from the repository.
     * @param aLong id of the client to be removed
     * @return an optional containing the client that was deleted, in case it was found
     * @throws Exception if the id is null
     */

    public CompletableFuture<Optional<Client>> delete(Long aLong){
        return CompletableFuture.supplyAsync(()-> {
            try {
                this.deleteAllOrdersByClientId(aLong);
                return repository.delete(aLong);
            } catch (Exception e) {
                throw new RuntimeException(e.getCause());
            }
        });
    }

    /**
     * Update a client in the repository
     * @param entity the client with the updated fields
     * @return optional containing the updated client, in case it was found
     * @throws Exception in case of invalid client
     */
    public CompletableFuture<Optional<Client>> update(Client entity) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return repository.update(entity);
            } catch (Exception e) {
                throw new RuntimeException(e.getCause());
            }
        });
    }

    /**
     * Check if a client exists in the repository
     * @param id the ID of the client we are looking for
     * @return true or false
     */

    public CompletableFuture<Boolean> existsClient(Long id) {
        return CompletableFuture.supplyAsync(() ->
                repository.findOne(id).isPresent());
    }
    /**
     * Get all the clients in the repository
     * @return returns all clients from the repository
     */

    public CompletableFuture<Set<Client>> getAllClients() {
        return CompletableFuture.supplyAsync(() ->
                new HashSet<>(repository.findAll())
        );
    }

    /**
     * Returns all clients whose name contain the given string.
     *
     * @param s String to be contained in the name.
     * @return Set of clients whose name contain a given string.
     */
    public CompletableFuture<Set<Client>> filterClientsByName(String s) {
        return CompletableFuture.supplyAsync(() -> {
            Iterable<Client> clients = repository.findAll();
            Set<Client> filteredClients = new HashSet<>();
            clients.forEach(filteredClients::add);
            filteredClients.removeIf(client -> !client.getName().contains(s));
            return filteredClients;
        });
    }


    /**
     * Removes all orders with a given clientId from the repository
     * @param clientId the given id
     */


    public CompletableFuture<Void> deleteAllOrdersByClientId(Long clientId) {
        orderRepository.findAll().stream()
                .filter((order) -> order.getClientId().equals(clientId))
                .map((BaseEntity::getId))
                .forEach(id -> {
                    try {
                        orderRepository.delete(id);
                    } catch (Exception e) {
                        throw new RuntimeException(e.getCause());
                    }
                });
        return null;
    }
}
