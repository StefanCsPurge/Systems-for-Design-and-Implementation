package ui;

import domain.Ammunition;
import service.AmmoService;

import java.util.Optional;

public class UpdateAmmoCommand extends Command{
    AmmoService ammoService;

    public UpdateAmmoCommand(String key, String description, AmmoService ammoService) {
        super(key, description);
        this.ammoService = ammoService;
    }

    @Override
    public void execute(){
        Optional<Ammunition> optionalAmmo = AddAmmoCommand.readAmmo();
        optionalAmmo.ifPresent(ammo -> {
            try {
                ammoService.update(ammo);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }
}
