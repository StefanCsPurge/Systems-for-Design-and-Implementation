package ro.ubb.gunstore.client.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ro.ubb.gunstore.web.dto.GunsDto;

import java.util.concurrent.CompletableFuture;

@Component
public class SortGunCommand extends Command{
    @Autowired
    private RestTemplate restTemplate;

    public SortGunCommand() {
        super("sortGunsPrice", "show the guns sorted ascending by price");
    }

    public SortGunCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void execute() {
        System.out.println("Now you can keep using our app while the guns are being sorted...");
        CompletableFuture.supplyAsync(() ->
               restTemplate.getForObject("http://localhost:8080/api/guns/sortPrice", GunsDto.class))
               .thenAcceptAsync(System.out::println);
        /*GunsDto gunsDto = restTemplate.getForObject("http://localhost:8080/api/guns/sortPrice", GunsDto.class);
        System.out.println(gunsDto);*/
    }
}
