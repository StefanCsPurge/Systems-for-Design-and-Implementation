package ro.ubb.gunstore.client.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ro.ubb.gunstore.web.dto.AccessoryDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

@Component
public class UpdateAccessoriesCommand extends Command {
    @Autowired
    private RestTemplate restTemplate;

    public UpdateAccessoriesCommand() {
        super("updateAccessories", "update a specific accessory");
    }

    public UpdateAccessoriesCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void execute() throws IOException {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("id: ");
        Long id = Long.valueOf(bufferRead.readLine());
        Optional<AccessoryDto> optionalAccessory = AddAccessoriesCommand.readAccessory();
        optionalAccessory.ifPresent(accessoryDto -> {
            try {
                accessoryDto.setId(id);
                restTemplate.put("http://localhost:8080/api/accessories/{id}", accessoryDto, id);

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        });
    }
}
