package ro.ubb.gunstore.client.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ro.ubb.gunstore.web.dto.GunDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

@Component
public class UpdateGunCommand extends Command {
    @Autowired
    private RestTemplate restTemplate;

    public UpdateGunCommand() {
        super("updateGun", "update a specific gun");
    }

    public UpdateGunCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void execute() throws IOException {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("id: ");
        Long id = Long.valueOf(bufferRead.readLine());
        Optional<GunDto> optionalGun = AddGunCommand.readGun();
        optionalGun.ifPresent(gunDto -> {
            try {
                gunDto.setId(id);
                restTemplate.put("http://localhost:8080/api/guns/{id}", gunDto, id);

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        });
    }
}
