package ui;

import service.GunServiceClient;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

public class PrintTop3GunsCommand extends Command{
    GunServiceClient gunService;

    public PrintTop3GunsCommand(String key, String description, GunServiceClient gunService) {
        super(key, description);
        this.gunService = gunService;
    }

    @Override
    public void execute() throws ExecutionException, InterruptedException {
        gunService.getTop3SoldGuns().thenAcceptAsync(top3Guns -> {
            System.out.println("\n");
            AtomicInteger i = new AtomicInteger(1);
            top3Guns.forEach(gun -> {
                System.out.println(i + ". " + gun);
                i.set(i.get() + 1);
            });
        });
    }
}
