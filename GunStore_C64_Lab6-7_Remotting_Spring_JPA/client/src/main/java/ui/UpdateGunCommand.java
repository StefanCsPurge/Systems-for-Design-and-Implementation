package ui;

import domain.Gun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.GunServiceClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

@Component
public class UpdateGunCommand extends Command {
    @Autowired
    private GunServiceClient gunService;

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
        Optional<Gun> optionalGun = AddGunCommand.readGun();
        optionalGun.ifPresent((gun) -> {
            try {
                gun.setId(id);
                gunService.updateGun(gun);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        });
    }
}
