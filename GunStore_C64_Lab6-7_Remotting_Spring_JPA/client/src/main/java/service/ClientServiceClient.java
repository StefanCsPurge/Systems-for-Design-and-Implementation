package service;

import domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ClientServiceClient implements ClientServiceInterface {
    @Qualifier("clientServiceServer")
    @Autowired
    private ClientServiceInterface clientService;

    @Override
    public void addClient(Client client) throws Exception {
        clientService.addClient(client);
    }

    @Override
    public Client delete(Long aLong) throws Exception {
        return clientService.delete(aLong);
    }

    @Override
    public void update(Client entity) throws Exception {
        clientService.update(entity);
    }

    @Override
    public Boolean existsClient(Long id) throws Exception {
        return clientService.existsClient(id);
    }

    @Override
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @Override
    public Set<Client> filterClientsByName(String s) {
        return clientService.filterClientsByName(s);
    }

    @Override
    public void deleteAllOrdersByClientId(Long clientId) {}
}
