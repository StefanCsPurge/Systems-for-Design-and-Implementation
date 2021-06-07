package ui;

import domain.Gun;
import service.GunService;
import java.util.List;

public class SortGunCommand extends Command{
    GunService gunService;

    public SortGunCommand(String key, String description, GunService gunService) {
        super(key, description);
        this.gunService = gunService;
    }

    @Override
    public void execute() {
        List<Gun> guns = gunService.getSortedGuns();
        guns.forEach(System.out::println);
    }
}
