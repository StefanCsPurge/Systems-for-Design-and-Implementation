package ui;

import domain.Gun;
import service.GunServiceInterface;

import java.util.Optional;

public class UpdateGunCommand extends Command {
    GunServiceInterface gunService;

    public UpdateGunCommand(String key, String description, GunServiceInterface gunService) {
        super(key, description);
        this.gunService = gunService;
    }

    @Override
    public void execute(){
        Optional<Gun> optionalGun = AddGunCommand.readGun();
        optionalGun.ifPresent((gun) -> {
            try {
                gunService.update(gun);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }
}
