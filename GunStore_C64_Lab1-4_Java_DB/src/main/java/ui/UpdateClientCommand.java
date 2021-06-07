package ui;

import domain.Client;
import service.ClientService;

import java.util.Optional;

public class UpdateClientCommand extends Command {
    ClientService clientService;

    public UpdateClientCommand(String key, String description, ClientService clientService) {
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
