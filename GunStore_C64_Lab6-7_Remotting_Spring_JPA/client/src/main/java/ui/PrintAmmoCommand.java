package ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.AmmoServiceClient;

@Component
public class PrintAmmoCommand extends Command {
    @Autowired
    private AmmoServiceClient ammoService;

    public PrintAmmoCommand() {
        super("printAmmo", "prints all ammo");
    }

    public PrintAmmoCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void execute() {
        ammoService.getAllAmmunition().forEach(System.out::println);
    }
}
