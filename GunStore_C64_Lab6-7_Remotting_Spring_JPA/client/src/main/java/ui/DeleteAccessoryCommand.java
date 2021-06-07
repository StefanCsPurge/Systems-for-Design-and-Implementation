package ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.GunAccessoryServiceClient;
import service.GunServiceClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class DeleteAccessoryCommand extends Command {
    @Autowired
    private GunAccessoryServiceClient gunAccessoryService;

    public DeleteAccessoryCommand() {
        super("deleteAccessory", "deletes a gunAccessory from the repository");
    }

    public DeleteAccessoryCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void execute() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.print("gunId: ");
            Long id = Long.valueOf(bufferRead.readLine());
            var deletedGunAccessory = gunAccessoryService.deleteAccessory(id);
            System.out.println("The following gunAccessory: " + deletedGunAccessory + " was deleted.");

        } catch (Exception e){
            /*e.printStackTrace();*/
            System.out.println(e.getMessage());
        }
    }
}
