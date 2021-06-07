package ro.ubb.gunstore.client.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ro.ubb.gunstore.web.dto.AccessoriesDto;
import ro.ubb.gunstore.web.dto.GunsDto;

@Component
public class PrintAccessoriesCommand extends Command {
    @Autowired
    private RestTemplate restTemplate;

    public PrintAccessoriesCommand() {
        super("printAccessories","prints all accessories");
    }

    public PrintAccessoriesCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void execute() {

        AccessoriesDto accessoriesDto = restTemplate.getForObject("http://localhost:8080/api/accessories", AccessoriesDto.class);
        System.out.println(accessoriesDto);
    }
}
