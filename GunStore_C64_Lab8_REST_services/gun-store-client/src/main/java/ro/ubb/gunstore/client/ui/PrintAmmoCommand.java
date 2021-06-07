package ro.ubb.gunstore.client.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ro.ubb.gunstore.web.dto.AmmosDto;
import ro.ubb.gunstore.web.dto.GunsDto;

@Component
public class PrintAmmoCommand extends Command {
    @Autowired
    private RestTemplate restTemplate;

    public PrintAmmoCommand() {
        super("printAmmo","prints all ammunition");
    }

    public PrintAmmoCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void execute() {

        AmmosDto ammoDto = restTemplate.getForObject("http://localhost:8080/api/ammunition", AmmosDto.class);
        System.out.println(ammoDto);
    }
}
