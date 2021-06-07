package ui;

import domain.Client;
import service.ClientService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

public class DeleteClientCommand extends Command {
    private final ClientService clientService;

    public DeleteClientCommand(String key, String description, ClientService clientService) {
        super(key, description);
        this.clientService = clientService;
    }

    @Override
    public void execute() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.print("clientId: ");
            Long id = Long.valueOf(bufferRead.readLine());
            Optional<Client> deletedClient = clientService.delete(id);
            if(deletedClient.isEmpty())
                System.out.println("Nothing was deleted.");
            else
                System.out.println("The following client: " + deletedClient.get() + " was deleted.");
        } catch (Exception e){
            /*e.printStackTrace();*/
            System.out.println(e.getMessage());
        }
    }
}
