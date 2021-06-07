package ro.ubb.gunstore.client.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ro.ubb.gunstore.web.dto.ClientDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

@Component
public class UpdateClientCommand extends Command {
    @Autowired
    private RestTemplate restTemplate;

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
        Optional<ClientDto> optionalClient = AddClientCommand.readClient();
        optionalClient.ifPresent(clientDto -> {
            try {
                clientDto.setId(id);
                restTemplate.put("http://localhost:8080/api/clients/{id}", clientDto, id);

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        });
    }
}
