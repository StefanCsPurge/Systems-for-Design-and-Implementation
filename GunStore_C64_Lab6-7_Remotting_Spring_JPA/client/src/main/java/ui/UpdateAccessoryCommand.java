package ui;

import domain.Gun;
import domain.GunAccessories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.GunAccessoryServiceClient;
import service.GunServiceClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

@Component
public class UpdateAccessoryCommand extends Command {
    @Autowired
    private GunAccessoryServiceClient gunAccessoryService;

    public UpdateAccessoryCommand() {
        super("updateGunAccessory", "update a specific gun accessory");
    }

    public UpdateAccessoryCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void execute() throws IOException {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("id: ");
        Long id = Long.valueOf(bufferRead.readLine());
        Optional<GunAccessories> optionalGunAccessories = AddAccessoryCommand.readGunAccessories();
        optionalGunAccessories.ifPresent((gunAccessories) -> {
            try {
                gunAccessories.setId(id);
                gunAccessoryService.updateAccessory(gunAccessories);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        });
    }
}
