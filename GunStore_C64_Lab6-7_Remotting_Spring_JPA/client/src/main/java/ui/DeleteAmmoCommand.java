package ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.AmmoServiceClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class DeleteAmmoCommand extends Command {
    @Autowired
    private AmmoServiceClient ammoService;

    public DeleteAmmoCommand() {
        super("deleteAmmo", "deletes ammo from the repository");
    }

    public DeleteAmmoCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void execute() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.print("ammoId: ");
            Long id = Long.valueOf(bufferRead.readLine());
            var deleted = ammoService.delete(id);
            System.out.println("The following ammo: " + deleted + " was deleted.");
        } catch (Exception e){
            /*e.printStackTrace();*/
            System.out.println(e.getMessage());
        }
    }
}
