package ro.ubb.gunstore.client.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ro.ubb.gunstore.web.dto.AmmoDto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

@Component
public class AddAmmoCommand extends Command {
    @Autowired
    private RestTemplate restTemplate;

    public AddAmmoCommand() {
        super("addAmmo", "adds ammo to the repository");
    }

    public AddAmmoCommand(String key, String description) {
        super(key, description);
    }

    protected static Optional<AmmoDto> readAmmo(){
        System.out.println("Read Ammunition {id(auto-generated), noOfRounds, caliber, manufacturer, price}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("noOfRounds: ");
            int no = Integer.parseInt(bufferRead.readLine());
            System.out.print("caliber: ");
            float cal = Float.parseFloat(bufferRead.readLine());
            System.out.print("manufacturer: ");
            String manufacturer = bufferRead.readLine();
            System.out.print("price: ");
            float price = Float.parseFloat(bufferRead.readLine());

            return Optional.of(new AmmoDto(no, cal, manufacturer, price));

        }
        catch (Exception e){
            /* e.printStackTrace();*/
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public void execute() {
        Optional<AmmoDto> opt = readAmmo();
        opt.ifPresent(ammoDto -> {
            AmmoDto savedAmmo = restTemplate.postForObject(
                    "http://localhost:8080/api/ammunition",
                    ammoDto,
                    AmmoDto.class
            );
            if (savedAmmo == null)
                System.out.println("Ammunition could not be saved");
            else if (savedAmmo.getId() == null)
                System.out.println(savedAmmo.getManufacturer());
        });
    }
}
