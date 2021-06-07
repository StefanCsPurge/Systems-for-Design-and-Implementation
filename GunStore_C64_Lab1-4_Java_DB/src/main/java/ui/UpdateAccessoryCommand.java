package ui;

import domain.GunAccessories;
import service.AccessoryService;

import java.util.Optional;

public class UpdateAccessoryCommand extends Command {
    AccessoryService accessoryService;

    public UpdateAccessoryCommand(String key, String description, AccessoryService accessoryService) {
        super(key, description);
        this.accessoryService = accessoryService;
    }

    @Override
    public void execute() {
        Optional<GunAccessories> accessory = AddAccessoryCommand.readAccessory();
        accessory.ifPresent((acc) -> {
            try {
                accessoryService.update(acc);
            } catch (Exception e) {
                /*e.printStackTrace();*/
                System.out.println(e.getMessage());
            }
        });
    }
}
