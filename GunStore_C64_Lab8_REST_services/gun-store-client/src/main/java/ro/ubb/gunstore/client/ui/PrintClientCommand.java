package ro.ubb.gunstore.client.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ro.ubb.gunstore.web.dto.ClientsDto;

@Component
public class PrintClientCommand extends Command {
    @Autowired
    private RestTemplate restTemplate;

    public PrintClientCommand() {
        super("printClients","prints all the clients");
    }

    public PrintClientCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void execute() {

        ClientsDto clientsDto = restTemplate.getForObject("http://localhost:8080/api/clients", ClientsDto.class);
        System.out.println(clientsDto);
    }
}
