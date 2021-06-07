package ro.ubb.gunstore.client.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ro.ubb.gunstore.web.dto.GunsDto;

import java.util.concurrent.CompletableFuture;

@Component
public class PrintGunCommand extends Command {
    @Autowired
    private RestTemplate restTemplate;

    public PrintGunCommand() {
        super("printGuns","prints all the guns");
    }

    public PrintGunCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void execute() {
        // System.out.println("Now you can keep using our app while we get your guns...");
        // CompletableFuture.supplyAsync(() -> gunService.getAllGuns()).thenAcceptAsync(
        //         listaVietii -> listaVietii.forEach(System.out::println)
        // );
        GunsDto gunsDto = restTemplate.getForObject("http://localhost:8080/api/guns", GunsDto.class);
        System.out.println(gunsDto);
    }
}
