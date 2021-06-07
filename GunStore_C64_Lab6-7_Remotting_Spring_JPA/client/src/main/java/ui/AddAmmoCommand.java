package ui;

import domain.Ammunition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.AmmoServiceClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

@Component
public class AddAmmoCommand extends Command {
    @Autowired
    private AmmoServiceClient ammoService;

    public AddAmmoCommand() {
        super("addAmmo", "adds ammunition to the repository");
    }

    public AddAmmoCommand(String key, String description) {
        super(key, description);

    }

    protected static Optional<Ammunition> readAmmo(){
        System.out.println("Read Ammunition {id(auto-generated), noOfRounds, caliber, manufacturer, price}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            /*System.out.print("id: ");
            Long id = Long.valueOf(bufferRead.readLine());*/
            System.out.print("noOfRounds: ");
            int no = Integer.parseInt(bufferRead.readLine());
            System.out.print("caliber: ");
            float cal = Float.parseFloat(bufferRead.readLine());
            System.out.print("manufacturer: ");
            String manufacturer = bufferRead.readLine();
            System.out.print("price: ");
            float price = Float.parseFloat(bufferRead.readLine());

            return Optional.of(new Ammunition(no, cal, manufacturer, price));

        }
        catch (Exception e){
            /* e.printStackTrace();*/
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public void execute() {
        Optional<Ammunition> ammo = readAmmo();
        ammo.ifPresent((x) -> {
            try {
                ammoService.addAmmunition(x);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }

}
