package ui;

import domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.ClientServiceClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

@Component
public class UpdateClientCommand extends Command {
    @Autowired
    ClientServiceClient clientService;

    public UpdateClientCommand() {
        super("updateClient", "update a specific client");
    }

    public UpdateClientCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void execute() throws IOException {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("id: ");
        Long id = Long.valueOf(bufferRead.readLine());
        Optional<Client> optionalClient = AddClientCommand.readClient();
        optionalClient.ifPresent((client) -> {
            try {
                client.setId(id);
                clientService.update(client);
            } catch (Exception e) {
                /*e.printStackTrace();*/
                System.out.println(e.getMessage());
            }
        });
    }
}
