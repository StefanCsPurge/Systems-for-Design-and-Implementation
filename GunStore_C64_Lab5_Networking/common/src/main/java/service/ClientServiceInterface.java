package service;


import domain.BaseEntity;
import domain.Client;
import domain.Order;
import domain.exceptions.ValidatorException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public interface ClientServiceInterface {
    /**
     * Add a client to the repository.
     * @param client Client to be added.
     * @throws ValidatorException returns exception if client is not valid*/

    public CompletableFuture<Optional<Client>> addClient(Client client) throws Exception;

    /**
     * Delete a client from the repository.
     * @param aLong id of the client to be removed
     * @return an optional containing the client that was deleted, in case it was found
     * @throws Exception if the id is null
     */

    public CompletableFuture<Optional<Client>> delete(Long aLong) throws Exception;

    /**
     * Update a client in the repository
     * @param entity the client with the updated fields
     * @return optional containing the updated client, in case it was found
     * @throws Exception in case of invalid client
     */
    public CompletableFuture<Optional<Client>> update(Client entity) throws Exception;

    /**
     * Check if a client exists in the repository
     * @param id the ID of the client we are looking for
     * @return true or false
     */

    public CompletableFuture<Boolean> existsClient(Long id) throws Exception;
    /**
     * Get all the clients in the repository
     * @return returns all clients from the repository
     */

    public CompletableFuture<Set<Client>> getAllClients();

    /**
     * Returns all clients whose name contain the given string.
     *
     * @param s String to be contained in the name.
     * @return Set of clients whose name contain a given string.
     */
    public CompletableFuture<Set<Client>> filterClientsByName(String s);

    /**
     * Removes all orders with a given clientId from the repository
     * @param clientId the given id
     */


    public CompletableFuture<Void> deleteAllOrdersByClientId(Long clientId);
}
