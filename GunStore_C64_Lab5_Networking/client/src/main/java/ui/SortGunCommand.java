package ui;

import service.GunServiceInterface;

public class SortGunCommand extends Command{
    GunServiceInterface gunService;

    public SortGunCommand(String key, String description, GunServiceInterface gunService) {
        super(key, description);
        this.gunService = gunService;
    }

    @Override
    public void execute() {
        gunService.getSortedGuns().thenAcceptAsync(guns -> {
            System.out.println("\n");
            guns.forEach(System.out::println);
        });
    }
}
