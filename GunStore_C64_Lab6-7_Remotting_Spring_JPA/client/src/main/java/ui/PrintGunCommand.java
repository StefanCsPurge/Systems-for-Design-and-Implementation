package ui;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.GunServiceClient;

import java.util.concurrent.CompletableFuture;

@Component
public class PrintGunCommand extends Command {
    @Autowired
    private GunServiceClient gunService;

    public PrintGunCommand() {
        super("printGuns","prints all the guns");
    }

    public PrintGunCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void execute() {
       /* System.out.println("Now you can keep using our app while we get your guns...");
        CompletableFuture.supplyAsync(() -> gunService.getAllGuns()).thenAcceptAsync(
                listaVietii -> listaVietii.forEach(System.out::println)
        );*/
        gunService.getAllGuns().forEach(System.out::println);
    }
}
