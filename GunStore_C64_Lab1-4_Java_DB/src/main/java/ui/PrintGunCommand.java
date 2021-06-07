package ui;

import domain.Gun;
import service.GunService;

import java.util.Set;

public class PrintGunCommand extends Command {
    GunService gunService;

    public PrintGunCommand(String key, String description, GunService gunService) {
        super(key, description);
        this.gunService = gunService;
    }

    @Override
    public void execute() {
        Set<Gun> guns = gunService.getAllGuns();
        guns.forEach(System.out::println);
    }
}
