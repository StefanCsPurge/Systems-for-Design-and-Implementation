package ui;

import domain.GunAccessories;
import service.AccessoryService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

public class AddAccessoryCommand extends Command {
    AccessoryService accessoryService;

    public AddAccessoryCommand(String key, String description, AccessoryService accessoryService) {
        super(key, description);
        this.accessoryService = accessoryService;

    }

    protected static Optional<GunAccessories> readAccessory(){
        System.out.println("Read accessory {id, name, type, company, price}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("id: ");
            Long id = Long.
                    valueOf(bufferRead.readLine());
            System.out.print("name: ");
            String name = bufferRead.readLine();
            System.out.print("type: ");
            String type = bufferRead.readLine();
            System.out.print("company: ");
            String company = bufferRead.readLine();
            System.out.print("price: ");
            float price = Float.parseFloat(bufferRead.readLine());

            return Optional.of(new GunAccessories(id, name, type, company, price));


        }
        catch (Exception e){
            /*e.printStackTrace();*/
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public void execute() {
        Optional<GunAccessories> accessory = readAccessory();
        accessory.ifPresent((x) -> {
            try {
                accessoryService.addAccessory(x);
            } catch (Exception e) {
                /*e.printStackTrace();*/
                System.out.println(e.getMessage());
            }
        });
    }
}
