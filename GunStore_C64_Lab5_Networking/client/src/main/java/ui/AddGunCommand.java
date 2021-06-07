package ui;

import domain.Gun;
import service.GunServiceClient;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

public class AddGunCommand extends Command {
    GunServiceClient gunService;

    public AddGunCommand(String key, String description, GunServiceClient gunService) {
        super(key, description);
        this.gunService = gunService;

    }

    protected static Optional<Gun> readGun(){
        System.out.println("Read gun {id, model, manufacturer, type, weight, price}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("id: ");
            Long id = Long.valueOf(bufferRead.readLine());
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

            return Optional.of(new Gun(id, model, manufacturer, type, weight, price));

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
                var future = gunService.addGun(x);
                System.out.println("got future");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }
}
