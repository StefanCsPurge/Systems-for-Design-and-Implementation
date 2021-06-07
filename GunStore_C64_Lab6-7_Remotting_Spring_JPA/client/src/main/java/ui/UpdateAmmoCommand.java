package ui;

import domain.Ammunition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.AmmoServiceClient;

import java.util.Optional;

@Component
public class UpdateAmmoCommand extends Command {
    @Autowired
    private AmmoServiceClient ammoService;

    public UpdateAmmoCommand() {
        super("updateAmmo", "update a specific ammo");
    }

    public UpdateAmmoCommand(String key, String description) {
        super(key, description);
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
