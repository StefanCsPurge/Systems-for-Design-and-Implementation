package ro.ubb.gunstore.client.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ro.ubb.gunstore.web.dto.GunDto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;


@Component
public class AddGunCommand extends Command {
    @Autowired
    private RestTemplate restTemplate;

    public AddGunCommand() {
        super("addGun", "adds a gun to the repository");
    }

    public AddGunCommand(String key, String description) {
        super(key, description);
    }

    protected static Optional<GunDto> readGun(){
        System.out.println("Read gun {id(auto-generated), model, manufacturer, type, weight, price}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("model: ");
            String model = bufferRead.readLine();
            System.out.print("manufacturer: ");
            String manufacturer = bufferRead.readLine();
            System.out.print("type: ");
            String type = bufferRead.readLine();
            System.out.print("weight: ");
            float weight = Float.parseFloat(bufferRead.readLine());
            System.out.print("price: ");
            float price = Float.parseFloat(bufferRead.readLine());
            return Optional.of(new GunDto(model, manufacturer, type, weight, price, 0L));

        }
        catch (Exception e){
           /* e.printStackTrace();*/
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public void execute() {
        Optional<GunDto> opt = readGun();
        opt.ifPresent(gunDto -> {
                GunDto savedGun = restTemplate.postForObject(
                        "http://localhost:8080/api/guns",
                        gunDto,
                        GunDto.class
                        );
                if (savedGun == null)
                    System.out.println("Gun could not be saved");
                else if (savedGun.getId() == null)
                    System.out.println(savedGun.getModel());
            });
    }
}
