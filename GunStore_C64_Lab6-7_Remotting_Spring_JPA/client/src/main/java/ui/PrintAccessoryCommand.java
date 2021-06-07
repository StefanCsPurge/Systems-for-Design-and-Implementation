package ui;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.GunAccessoryServiceClient;
import service.GunServiceClient;

import java.util.concurrent.CompletableFuture;

@Component
public class PrintAccessoryCommand extends Command {
    @Autowired
    private GunAccessoryServiceClient gunAccessoryService;

    public PrintAccessoryCommand() {
        super("printGunAccessories","prints all the guns accessories");
    }

    public PrintAccessoryCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void execute() {
       /* System.out.println("Now you can keep using our app while we get your guns...");
        CompletableFuture.supplyAsync(() -> gunService.getAllGuns()).thenAcceptAsync(
                listaVietii -> listaVietii.forEach(System.out::println)
        );*/
        gunAccessoryService.getAllAccessories().forEach(System.out::println);
    }
}
