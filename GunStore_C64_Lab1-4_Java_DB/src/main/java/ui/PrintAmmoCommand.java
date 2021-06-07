package ui;

import domain.Ammunition;
import service.AmmoService;

import java.util.Set;

public class PrintAmmoCommand extends Command{
    AmmoService ammoService;

    public PrintAmmoCommand(String key, String description, AmmoService ammoService) {
        super(key, description);
        this.ammoService = ammoService;
    }

    @Override
    public void execute() {
        Set<Ammunition> guns = ammoService.getAllAmmo();
        guns.forEach(System.out::println);
    }
}
