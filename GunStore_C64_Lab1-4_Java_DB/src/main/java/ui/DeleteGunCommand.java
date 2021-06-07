package ui;

import domain.Gun;
import service.GunService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

public class DeleteGunCommand extends Command {
    private final GunService gunService;

    public DeleteGunCommand(String key, String description, GunService gunService) {
        super(key, description);
        this.gunService = gunService;
    }

    @Override
    public void execute() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.print("gunId: ");
            Long id = Long.valueOf(bufferRead.readLine());
            Optional<Gun> deletedGun = gunService.delete(id);
            if(deletedGun.isEmpty())
                System.out.println("Nothing was deleted.");
            else
                System.out.println("The following gun: " + deletedGun.get() + " was deleted.");
        } catch (Exception e){
            /*e.printStackTrace();*/
            System.out.println(e.getMessage());
        }
    }
}
