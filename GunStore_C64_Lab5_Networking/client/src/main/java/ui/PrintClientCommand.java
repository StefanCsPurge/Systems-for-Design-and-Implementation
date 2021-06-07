package ui;

import domain.Client;
import service.ClientServiceClient;

import java.util.Set;

public class PrintClientCommand extends Command {
    ClientServiceClient clientService;

    public PrintClientCommand(String key, String description, ClientServiceClient clientService) {
        super(key, description);
        this.clientService = clientService;
    }

    @Override
    public void execute() {
        clientService.getAllClients().thenAcceptAsync(clients -> {
            System.out.println("\n");
            clients.forEach(System.out::println);
        });
    }
}
