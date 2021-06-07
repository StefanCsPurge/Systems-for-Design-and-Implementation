package ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.GunServiceClient;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class PrintTop3GunsCommand extends Command{
    @Autowired
    private GunServiceClient gunService;

    public PrintTop3GunsCommand() {
        super("printTop3Guns", "prints the top 3 of the most sold guns");
    }

    public PrintTop3GunsCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void execute() throws ExecutionException, InterruptedException {
        var top3Guns = gunService.getTop3SoldGuns();
        AtomicInteger i = new AtomicInteger(1);
        top3Guns.forEach(gun -> {
            System.out.println(i + ". " + gun + " sold " + gun.getTimesSold() + " times");
            i.set(i.get() + 1);
        });
    }
}
