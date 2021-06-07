package ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.GunServiceClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class DeleteGunCommand extends Command {
    @Autowired
    private GunServiceClient gunService;

    public DeleteGunCommand() {
        super("deleteGun", "deletes a gun from the repository");
    }

    public DeleteGunCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void execute() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.print("gunId: ");
            Long id = Long.valueOf(bufferRead.readLine());
            var deletedGun = gunService.deleteGun(id);
            System.out.println("The following gun: " + deletedGun + " was deleted.");

        } catch (Exception e){
            /*e.printStackTrace();*/
            System.out.println(e.getMessage());
        }
    }
}
