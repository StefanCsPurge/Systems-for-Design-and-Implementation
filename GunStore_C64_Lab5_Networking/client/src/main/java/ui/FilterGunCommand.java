package ui;

import service.GunServiceClient;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class FilterGunCommand extends Command{
    GunServiceClient gunService;

    public FilterGunCommand(String key, String description, GunServiceClient gunService) {
        super(key, description);
        this.gunService = gunService;
    }

    @Override
    public void execute() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("model: ");
            String model = bufferRead.readLine();
            gunService.filterGunsByModel(model).thenAcceptAsync(guns -> {
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
