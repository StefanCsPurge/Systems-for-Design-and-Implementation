package ui;

import domain.Client;
import service.ClientServiceClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

public class DeleteClientCommand extends Command {
    private final ClientServiceClient clientService;

    public DeleteClientCommand(String key, String description, ClientServiceClient clientService) {
        super(key, description);
        this.clientService = clientService;
    }

    @Override
    public void execute() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.print("clientId: ");
            Long id = Long.valueOf(bufferRead.readLine());
            clientService.delete(id).thenAcceptAsync(deletedClient -> { if(deletedClient.isEmpty())
                System.out.println("Nothing was deleted.");
            else
                System.out.println("The following client: " + deletedClient.get() + " was deleted.");});

        } catch (Exception e){
            /*e.printStackTrace();*/
            System.out.println(e.getMessage());
        }
    }
}
