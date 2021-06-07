package ui;

import domain.Gun;
import domain.GunAccessories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.GunAccessoryServiceClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;


@Component
public class AddAccessoryCommand extends Command {
    @Autowired
    private GunAccessoryServiceClient accessoryService;

    public AddAccessoryCommand() {
        super("addAccessory", "adds an accessory to the repository");
    }

    public AddAccessoryCommand(String key, String description) {
        super(key, description);
    }

    protected static Optional<GunAccessories> readGunAccessories(){
        System.out.println("Read gunAccessory {id(auto-generated), name, type, company, price}");

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
            return Optional.of(new GunAccessories(name, type, company, price));

        }
        catch (Exception e){
            /* e.printStackTrace();*/
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public void execute() {
        Optional<GunAccessories> gunAccessories = readGunAccessories();
        gunAccessories.ifPresent((x) -> {
            try {
                accessoryService.addAccessory(x);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }
}
