package ro.ubb.gunstore.client.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ro.ubb.gunstore.web.dto.ClientDto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;


@Component
public class AddClientCommand extends Command {
    @Autowired
    private RestTemplate restTemplate;

    public AddClientCommand() {
        super("addClient", "adds a client to the repository");
    }

    public AddClientCommand(String key, String description) {
        super(key, description);
    }

    protected static Optional<ClientDto> readClient(){
        System.out.println("Read client {id(auto-generated), name, cnp, budget}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("name: ");
            String name = bufferRead.readLine();
            System.out.print("cnp: ");
            String cnp = bufferRead.readLine();
            System.out.print("budget: ");
            float budget = Float.parseFloat(bufferRead.readLine());

            return Optional.of(new ClientDto(name, cnp, budget));

        }
        catch (Exception e){
            /*e.printStackTrace();*/
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public void execute() {
        Optional<ClientDto> opt = readClient();
        opt.ifPresent(clientDto -> {
            ClientDto savedClient = restTemplate.postForObject(
                    "http://localhost:8080/api/clients",
                    clientDto,
                    ClientDto.class
            );
            if (savedClient == null)
                System.out.println("Client could not be saved");
            else if (savedClient.getId() == null)
                System.out.println(savedClient.getName());
        });
    }
}
