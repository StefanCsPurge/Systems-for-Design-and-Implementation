package ui;

import domain.Gun;
import service.GunService;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;

public class FilterGunCommand extends Command{
    GunService gunService;

    public FilterGunCommand(String key, String description, GunService gunService) {
        super(key, description);
        this.gunService = gunService;
    }

    @Override
    public void execute() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("model: ");
            String model = bufferRead.readLine();
            Set<Gun> guns = gunService.filterGunsByModel(model);
            guns.forEach(System.out::println);
        }
        catch (Exception e){
            /*e.printStackTrace();*/
            System.out.println(e.getMessage());
        }
    }
}
