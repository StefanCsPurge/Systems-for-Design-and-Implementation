package ui;

import domain.GunAccessories;
import service.AccessoryService;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;

public class FilterAccesoriesCommand extends Command{
    AccessoryService accessoryService;

    public FilterAccesoriesCommand(String key, String description, AccessoryService accessoryService) {
        super(key, description);
        this.accessoryService = accessoryService;
    }

    @Override
    public void execute() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("min: ");
            int min = Integer.parseInt(bufferRead.readLine());
            System.out.print("max: ");
            int max = Integer.parseInt(bufferRead.readLine());
            Set<GunAccessories> gunAccessories = accessoryService.filterByPrice(min,max);
            gunAccessories.forEach(System.out::println);
        }
        catch (Exception e){
            /*e.printStackTrace();*/
            System.out.println(e.getMessage());
        }
    }
}
