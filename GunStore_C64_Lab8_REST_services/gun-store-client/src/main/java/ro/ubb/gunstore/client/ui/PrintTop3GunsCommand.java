package ro.ubb.gunstore.client.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ro.ubb.gunstore.web.dto.GunsDto;

import java.util.concurrent.ExecutionException;

@Component
public class PrintTop3GunsCommand extends Command{
    @Autowired
    private RestTemplate restTemplate;

    public PrintTop3GunsCommand() {
        super("printTop3Guns", "prints the top 3 of the most sold guns");
    }

    public PrintTop3GunsCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void execute() throws ExecutionException, InterruptedException {
        GunsDto top3gunsDto = restTemplate.getForObject("http://localhost:8080/api/guns/top3sold", GunsDto.class);
        System.out.println("Top 3 guns sold are:");
        System.out.println(top3gunsDto);
    }
}
