package ui;
import service.GunServiceClient;

public class PrintGunCommand extends Command {
    GunServiceClient gunService;

    public PrintGunCommand(String key, String description, GunServiceClient gunService) {
        super(key, description);
        this.gunService = gunService;
    }

    @Override
    public void execute() {
        gunService.getAllGuns().thenAcceptAsync(guns -> {
            System.out.println("\n");
            guns.forEach(System.out::println);
        });
    }
}
