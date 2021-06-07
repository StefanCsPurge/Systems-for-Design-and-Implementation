package ui;

import domain.Ammunition;
import service.AmmoService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

public class DeleteAmmoCommand extends Command{
    private final AmmoService ammoService;

    public DeleteAmmoCommand(String key, String description, AmmoService ammoService) {
        super(key, description);
        this.ammoService = ammoService;
    }

    @Override
    public void execute() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.print("ammoId: ");
            Long id = Long.valueOf(bufferRead.readLine());
            Optional<Ammunition> deletedAmmo = ammoService.delete(id);
            if(deletedAmmo.isEmpty())
                System.out.println("Nothing was deleted.");
            else
                System.out.println("The following ammo: " + deletedAmmo.get() + " was deleted.");
        } catch (Exception e){
            /*e.printStackTrace();*/
            System.out.println(e.getMessage());
        }
    }
}
