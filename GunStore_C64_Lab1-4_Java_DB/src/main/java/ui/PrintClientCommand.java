package ui;

import domain.Client;
import service.ClientService;

import java.util.Set;

public class PrintClientCommand extends Command {
    ClientService clientService;

    public PrintClientCommand(String key, String description, ClientService clientService) {
        super(key, description);
        this.clientService = clientService;
    }

    @Override
    public void execute() {
        Set<Client> clients = clientService.getAllClients();
        clients.forEach(System.out::println);
    }
}
