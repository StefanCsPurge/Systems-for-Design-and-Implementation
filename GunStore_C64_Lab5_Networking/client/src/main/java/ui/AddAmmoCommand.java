package ui;

import domain.Ammunition;
import service.AmmoServiceClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

public class AddAmmoCommand extends Command {
    AmmoServiceClient ammoService;

    public AddAmmoCommand(String key, String description, AmmoServiceClient ammoService) {
        super(key, description);
        this.ammoService = ammoService;

    }

    protected static Optional<Ammunition> readAmmo(){
        System.out.println("Read Ammunition {id, noOfRounds, caliber, manufacturer, price}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("id: ");
            Long id = Long.valueOf(bufferRead.readLine());
            System.out.print("noOfRounds: ");
            int no = Integer.parseInt(bufferRead.readLine());
            System.out.print("caliber: ");
            float cal = Float.parseFloat(bufferRead.readLine());
            System.out.print("manufacturer: ");
            String manufacturer = bufferRead.readLine();
            System.out.print("price: ");
            float price = Float.parseFloat(bufferRead.readLine());

            return Optional.of(new Ammunition(id, no, cal, manufacturer, price));

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
