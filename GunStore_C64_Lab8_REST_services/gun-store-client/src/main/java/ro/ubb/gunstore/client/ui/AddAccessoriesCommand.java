package ro.ubb.gunstore.client.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ro.ubb.gunstore.web.dto.AccessoryDto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

@Component
public class AddAccessoriesCommand extends Command {
    @Autowired
    private RestTemplate restTemplate;

    public AddAccessoriesCommand() {
        super("addAccessories", "adds an accessory to the repository");
    }

    public AddAccessoriesCommand(String key, String description) {
        super(key, description);
    }

    protected static Optional<AccessoryDto> readAccessory(){
        System.out.println("Read gunAccessory {id(auto-generated), name, type, company, price, gunID}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("name: ");
            String name = bufferRead.readLine();
            System.out.print("type: ");
            String type = bufferRead.readLine();
            System.out.print("company: ");
            String company = bufferRead.readLine();
            System.out.print("price: ");
            float price = Float.parseFloat(bufferRead.readLine());
            System.out.print("gunId: ");
            Long gunID = Long.parseLong(bufferRead.readLine());
            return Optional.of(new AccessoryDto(name, type, company, price, gunID));

        }
        catch (Exception e){
            /* e.printStackTrace();*/
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public void execute() {
        Optional<AccessoryDto> opt = readAccessory();
        opt.ifPresent(accessoryDto -> {
            AccessoryDto savedAccessory = restTemplate.postForObject(
                    "http://localhost:8080/api/accessories",
                    accessoryDto,
                    AccessoryDto.class
            );
            if (savedAccessory == null)
                System.out.println("Accessory could not be saved");
            else if (savedAccessory.getId() == null)
                System.out.println(savedAccessory.getName());
        });
    }
}
