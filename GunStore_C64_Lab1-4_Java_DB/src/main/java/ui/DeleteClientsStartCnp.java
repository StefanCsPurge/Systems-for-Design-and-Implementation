package ui;

import domain.Client;
import domain.Gun;
import service.ClientService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.Set;

public class DeleteClientsStartCnp extends Command{
    private final ClientService clientService;

    public DeleteClientsStartCnp(String key, String description, ClientService clientService) {
        super(key, description);
        this.clientService = clientService;
    }

    @Override
    public void execute() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.print("Cnp string: ");
            String cnp = String.valueOf(bufferRead.readLine());
            Set<Client> client_list = clientService.getAllClients();

            client_list.forEach(client->{
                if(client.getCnp().startsWith(cnp)) {
                    System.out.println("The following client: " + client.getId() + " was deleted.");
                    try {
                        clientService.delete(client.getId());
                    } catch (Exception e) {
                        /*e.printStackTrace();*/
                        System.out.println(e.getMessage());
                    }
                }
            });

        } catch (Exception e){
            /*e.printStackTrace();*/
            System.out.println(e.getMessage());
        }
    }
}
