package ui;

import domain.Client;
import service.ClientService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

public class AddClientCommand extends Command {
    ClientService clientService;

    public AddClientCommand(String key, String description, ClientService clientService) {
        super(key, description);
        this.clientService = clientService;

    }

    protected static Optional<Client> readClient(){
        System.out.println("Read client {id, name, cnp, budget}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("id: ");
            Long id = Long.valueOf(bufferRead.readLine());
            System.out.print("name: ");
            String name = bufferRead.readLine();
            System.out.print("cnp: ");
            String cnp = bufferRead.readLine();
            System.out.print("budget: ");
            float budget = Float.parseFloat(bufferRead.readLine());

            return Optional.of(new Client(id, name, cnp, budget));

        }
        catch (Exception e){
            /*e.printStackTrace();*/
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public void execute() {
        Optional<Client> client = readClient();
        client.ifPresent((x) -> {
            try {
                clientService.addClient(x);
            } catch (Exception e) {
                /*e.printStackTrace();*/
                System.out.println(e.getMessage());
            }
        });
    }
}
