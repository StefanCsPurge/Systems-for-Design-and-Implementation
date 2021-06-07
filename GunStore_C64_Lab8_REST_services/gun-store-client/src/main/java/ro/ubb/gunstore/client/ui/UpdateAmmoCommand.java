package ro.ubb.gunstore.client.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ro.ubb.gunstore.web.dto.AmmoDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

@Component
public class UpdateAmmoCommand extends Command {
    @Autowired
    private RestTemplate restTemplate;

    public UpdateAmmoCommand() {
        super("updateAmmo", "update a specific ammunition");
    }

    public UpdateAmmoCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void execute() throws IOException {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("id: ");
        Long id = Long.valueOf(bufferRead.readLine());
        Optional<AmmoDto> optionalAmmo = AddAmmoCommand.readAmmo();
        optionalAmmo.ifPresent(ammoDto -> {
            try {
                ammoDto.setId(id);
                restTemplate.put("http://localhost:8080/api/ammunition/{id}", ammoDto, id);

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        });
    }
}
