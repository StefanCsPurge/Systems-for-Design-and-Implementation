package ui;

import domain.Ammunition;
import service.AmmoServiceClient;

import java.util.Set;

public class PrintAmmoCommand extends Command {
    AmmoServiceClient ammoService;

    public PrintAmmoCommand(String key, String description, AmmoServiceClient ammoService) {
        super(key, description);
        this.ammoService = ammoService;
    }

    @Override
    public void execute() {
        ammoService.getAllAmmunition().thenAcceptAsync(ammos -> {
            System.out.println("\n");
            ammos.forEach(System.out::println);
        });
    }
}
