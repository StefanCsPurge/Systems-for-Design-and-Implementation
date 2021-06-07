package ui;

import domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.ClientServiceClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

@Component
public class AddClientCommand extends Command {
    @Autowired
    private ClientServiceClient clientService;

    public AddClientCommand() {
        super("addClient", "adds a client to the repository");
    }

    public AddClientCommand(String key, String description) {
        super(key, description);
    }

    protected static Optional<Client> readClient(){
        System.out.println("Read client {id(auto-generated), name, cnp, budget}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("name: ");
            String name = bufferRead.readLine();
            System.out.print("cnp: ");
            String cnp = bufferRead.readLine();
            System.out.print("budget: ");
            float budget = Float.parseFloat(bufferRead.readLine());

            return Optional.of(new Client(name, cnp, budget));

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
