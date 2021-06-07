package ui;

import service.GunServiceClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ListGunTypeCommand extends Command{
    GunServiceClient gunService;

    public ListGunTypeCommand(String key, String description, GunServiceClient gunService) {
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
            gunService.filterGunsByType(str).thenAcceptAsync(guns -> {
                System.out.println("\n");
                guns.forEach(System.out::println);
            });
        }
        catch (Exception e){
            /*e.printStackTrace();*/
            System.out.println(e.getMessage());
        }
    }
}
