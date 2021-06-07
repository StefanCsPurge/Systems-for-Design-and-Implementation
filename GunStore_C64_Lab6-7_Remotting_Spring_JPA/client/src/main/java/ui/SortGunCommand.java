package ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.GunServiceClient;

import java.util.concurrent.CompletableFuture;

@Component
public class SortGunCommand extends Command{
    @Autowired
    private GunServiceClient gunService;

    public SortGunCommand() {
        super("sortGunsPrice", "show the guns sorted ascending by price");
    }

    public SortGunCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void execute() {
        System.out.println("Now you can keep using our app while the guns are being sorted...");
        CompletableFuture.supplyAsync(() -> gunService.getSortedGuns()).thenAcceptAsync(
                listaVietii -> listaVietii.forEach(System.out::println)
        );
    }
}
