package ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.ClientServiceClient;

@Component
public class PrintClientCommand extends Command {
    @Autowired
    private ClientServiceClient clientService;

    public PrintClientCommand() {
        super("printClients", "prints all the clients");
    }

    public PrintClientCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void execute() {
        clientService.getAllClients().forEach(System.out::println);
    }
}
