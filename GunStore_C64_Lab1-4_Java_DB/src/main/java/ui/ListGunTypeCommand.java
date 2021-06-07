package ui;

import domain.Gun;
import service.GunService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;

public class ListGunTypeCommand extends Command{
    GunService gunService;

    public ListGunTypeCommand(String key, String description, GunService gunService) {
        super(key, description);
        this.gunService = gunService;
    }

    /**
     * List all guns whose type contains a given string.
     */
    @Override
    public void execute() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Input search string: ");
            String str = bufferRead.readLine();
            Set<Gun> guns = gunService.filterGunsByType(str);
            guns.forEach(System.out::println);
        }
        catch (Exception e){
            /*e.printStackTrace();*/
            System.out.println(e.getMessage());
        }
    }
}
