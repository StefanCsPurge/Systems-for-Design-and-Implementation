package ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.GunServiceClient;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class FilterGunCommand extends Command{
    @Autowired
    private GunServiceClient gunService;

    public FilterGunCommand() {
        super("filterGunsModel", "shows the guns with the specified model");
    }

    public FilterGunCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void execute() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("model: ");
            String model = bufferRead.readLine();
            gunService.filterGunsByModel(model).forEach(System.out::println);
        }
        catch (Exception e){
            /*e.printStackTrace();*/
            System.out.println(e.getMessage());
        }
    }
}
