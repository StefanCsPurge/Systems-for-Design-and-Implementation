package ui;

import domain.Gun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.GunServiceClient;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;


@Component
public class AddGunCommand extends Command {
    @Autowired
    private GunServiceClient gunService;

    public AddGunCommand() {
        super("addGun", "adds a gun to the repository");
    }

    public AddGunCommand(String key, String description) {
        super(key, description);
    }

    protected static Optional<Gun> readGun(){
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
            return Optional.of(new Gun(model, manufacturer, type, weight, price));

        }
        catch (Exception e){
           /* e.printStackTrace();*/
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public void execute() {
        Optional<Gun> gun = readGun();
        gun.ifPresent((x) -> {
            try {
                 gunService.addGun(x);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }
}
