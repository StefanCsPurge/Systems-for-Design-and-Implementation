package ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.GunServiceClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;


@Component
public class ListGunTypeCommand extends Command{
    @Autowired
    private GunServiceClient gunService;

    public ListGunTypeCommand() {
        super("listGunsTypeStr", "list all guns whose type contains a given string");
    }

    public ListGunTypeCommand(String key, String description) {
        super(key, description);
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
            gunService.filterGunsByType(str).forEach(System.out::println);
        }
        catch (Exception e){
            /*e.printStackTrace();*/
            System.out.println(e.getMessage());
        }
    }
}
