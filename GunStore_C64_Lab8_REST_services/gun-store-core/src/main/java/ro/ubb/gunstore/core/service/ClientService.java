package ro.ubb.gunstore.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.gunstore.core.model.BaseEntity;
import ro.ubb.gunstore.core.model.Client;
import ro.ubb.gunstore.core.model.exceptions.RepositoryException;
import ro.ubb.gunstore.core.model.validators.ClientValidator;
import ro.ubb.gunstore.core.repository.ClientRepository;
import ro.ubb.gunstore.core.repository.OrderRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Service for handling clients.
 */
@Service
public class ClientService implements ClientServiceInterface {
    private static final Logger log = LoggerFactory.getLogger(ClientService.class);

    @Autowired
    private ClientRepository repository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ClientValidator clientValidator;

    /**
     * Add a client to the repository.
     * @param client Client to be added. */
    public Client addClient(Client client) {
        log.trace("addClient - method entered: client={}", client);
        clientValidator.validate(client);
        var result = repository.save(client);
        log.trace("addClient - result={}", result);
        return result;
    }

    /**
     * Delete a client from the repository.
     * @param id id of the client to be removed
     * @return the client that was deleted, in case it was found
     */
    public Client delete(Long id) {
        log.trace("deleteClient - method entered: clientID={}", id);
        this.deleteAllOrdersByClientId(id);
        Optional<Client> opt = repository.findById(id);
        if(opt.isEmpty())
            throw new RepositoryException("Client with the id " + id + " is not in the repository.");
        repository.deleteById(id);
        log.trace("deleteClient: result={}",opt.get());
        return opt.get();
    }

    /**
     * Update a client in the repository
     * @param client the client with the updated fields
     */
    public Client update(Client client) {
        log.trace("updateClient - method entered: client={}", client);
        clientValidator.validate(client);
        var result = repository.save(client);
        log.debug("updateClient - updated: client={}", client);
        log.trace("updateClient - result={}", result);
        return result;
    }

    /**
     * Check if a client exists in the repository
     * @param id the ID of the client we are looking for
     * @return true or false
     */

    public Boolean existsClient(Long id) {
        log.trace("existsClient - method entered: clientID={}", id);
        var result = repository.findById(id).isPresent();
        log.trace("existsClient: result={}", result);
        return result;
    }
    /**
     * Get all the clients in the repository
     * @return returns all clients from the repository
     */

    @Override
    public List<Client> getAllClients() {
        log.trace("getAllClients - method entered");
        var result = repository.findAll();
        log.trace("getAllClients: result={}",result);
        return result;

    }

    /**
     * Returns all clients whose name contain the given string.
     *
     * @param s String to be contained in the name.
     * @return Set of clients whose name contain a given string.
     */
    public Set<Client> filterClientsByName(String s) {
        log.trace("filterClientsByName - method entered: filterStr={}", s);
        Iterable<Client> clients = repository.findAll();
        Set<Client> filteredClients = new HashSet<>();
        clients.forEach(filteredClients::add);
        filteredClients.removeIf(client -> !client.getName().contains(s));
        log.trace("filterClientsByName: result={}", filteredClients);
        return filteredClients;
    }

    /**
     * Removes all orders with a given clientId from the repository
     * @param clientId the given id
     */
    public void deleteAllOrdersByClientId(Long clientId) {
        log.trace("deleteAllOrdersByClientId - method entered");
        orderRepository.findAll().stream()
                .filter((order) -> order.getClientId().equals(clientId))
                .map((BaseEntity::getId))
                .forEach(id -> {
                    try {
                        orderRepository.deleteById(id);
                    } catch (Exception e) {
                        throw new RuntimeException(e.getCause());
                    }
                });
        log.trace("deleteAllOrdersByClientId - method finished");
    }
}
