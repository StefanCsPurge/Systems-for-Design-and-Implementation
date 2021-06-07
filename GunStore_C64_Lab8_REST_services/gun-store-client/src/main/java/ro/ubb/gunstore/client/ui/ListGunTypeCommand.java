package ro.ubb.gunstore.client.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ro.ubb.gunstore.web.dto.GunsDto;

import java.io.BufferedReader;
import java.io.InputStreamReader;


@Component
public class ListGunTypeCommand extends Command{
    @Autowired
    private RestTemplate restTemplate;

    public ListGunTypeCommand() {
        super("listGunsTypeStr", "list all guns whose type contains a given string");
    }

    public ListGunTypeCommand(String key, String description) {
        super(key, description);
    }

    /**
     * List all guns whose type contains a given string.
     */
    @Override
    public void execute() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Input search string: ");
            String typeStr = bufferRead.readLine();
            GunsDto gunsDto = restTemplate.getForObject("http://localhost:8080/api/guns/filterType/{typeStr}", GunsDto.class, typeStr);
            System.out.println(gunsDto);
        }
        catch (Exception e){
            /*e.printStackTrace();*/
            System.out.println(e.getMessage());
        }
    }
}
