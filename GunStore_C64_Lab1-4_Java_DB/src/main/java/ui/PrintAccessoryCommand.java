package ui;

import domain.GunAccessories;
import service.AccessoryService;

import java.util.Set;

public class PrintAccessoryCommand extends Command {
    AccessoryService accessoryService;

    public PrintAccessoryCommand(String key, String description, AccessoryService accessoryService) {
        super(key, description);
        this.accessoryService = accessoryService;
    }

    @Override
    public void execute() {
        Set<GunAccessories> accessory = accessoryService.getAllAccessories();
        accessory.forEach(System.out::println);
    }
}
