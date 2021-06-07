package ui;

import domain.Client;
import service.ClientServiceClient;

import java.util.Optional;

public class UpdateClientCommand extends Command {
    ClientServiceClient clientService;

    public UpdateClientCommand(String key, String description, ClientServiceClient clientService) {
        super(key, description);
        this.clientService = clientService;
    }

    @Override
    public void execute() {
        Optional<Client> optionalClient = AddClientCommand.readClient();
        optionalClient.ifPresent((client) -> {
            try {
                clientService.update(client);
            } catch (Exception e) {
                /*e.printStackTrace();*/
                System.out.println(e.getMessage());
            }
        });
    }
}
