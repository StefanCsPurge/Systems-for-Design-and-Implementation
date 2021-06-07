package ro.ubb.gunstore.client.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ro.ubb.gunstore.web.dto.GunsDto;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class FilterGunModelCommand extends Command {
    @Autowired
    private RestTemplate restTemplate;

    public FilterGunModelCommand() {
        super("filterGunsModel","show all the guns with a given model");
    }

    public FilterGunModelCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void execute() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("model: ");
            String model = bufferRead.readLine();
            GunsDto gunsDto = restTemplate.getForObject("http://localhost:8080/api/guns/filterModel/{model}", GunsDto.class, model);
            System.out.println(gunsDto);
        }
        catch (Exception e){
            /*e.printStackTrace();*/
            System.out.println(e.getMessage());
        }

    }
}
