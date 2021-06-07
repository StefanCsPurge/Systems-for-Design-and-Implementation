package service;


import domain.Client;
import domain.exceptions.ValidatorException;

import java.util.List;
import java.util.Set;

public interface ClientServiceInterface {
    /**
     * Add a client to the repository.
     * @param client Client to be added.
     * @throws ValidatorException returns exception if client is not valid*/
    void addClient(Client client) throws Exception;

    /**
     * Delete a client from the repository.
     * @param aLong id of the client to be removed
     * @return the client that was deleted, in case it was found
     * @throws Exception if the id is null
     */
    Client delete(Long aLong) throws Exception;

    /**
     * Update a client in the repository
     * @param entity the client with the updated fields
     * @throws Exception in case of invalid client
     */
    void update(Client entity) throws Exception;

    /**
     * Check if a client exists in the repository
     * @param id the ID of the client we are looking for
     * @return true or false
     */
    Boolean existsClient(Long id) throws Exception;

    /**
     * Get all the clients in the repository
     * @return returns all clients from the repository
     */
    List<Client> getAllClients();

    /**
     * Returns all clients whose name contain the given string.
     *
     * @param s String to be contained in the name.
     * @return Set of clients whose name contain a given string.
     */
    Set<Client> filterClientsByName(String s);

    /**
     * Removes all orders with a given clientId from the repository
     * @param clientId the given id
     */
    void deleteAllOrdersByClientId(Long clientId);
}
