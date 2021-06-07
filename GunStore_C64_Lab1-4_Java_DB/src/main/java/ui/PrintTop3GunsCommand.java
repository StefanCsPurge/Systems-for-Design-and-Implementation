package ui;

import domain.Gun;
import service.GunService;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PrintTop3GunsCommand extends Command{
    GunService gunService;

    public PrintTop3GunsCommand(String key, String description, GunService gunService) {
        super(key, description);
        this.gunService = gunService;
    }

    @Override
    public void execute() {
        List<Gun> top3Guns = gunService.getTop3SoldGuns();
        AtomicInteger i = new AtomicInteger(1);
        top3Guns.forEach(gun -> {
            System.out.println(i + ". " + gun);
            i.set(i.get() + 1);
        });
    }
}
