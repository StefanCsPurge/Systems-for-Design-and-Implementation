package ui;

import domain.Ammunition;
import service.AmmoServiceClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

public class DeleteAmmoCommand extends Command {
    private final AmmoServiceClient ammoService;

    public DeleteAmmoCommand(String key, String description, AmmoServiceClient ammoService) {
        super(key, description);
        this.ammoService = ammoService;
    }

    @Override
    public void execute() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.print("ammoId: ");
            Long id = Long.valueOf(bufferRead.readLine());
            ammoService.delete(id).thenAcceptAsync(deletedClient -> { if(deletedClient.isEmpty())
                System.out.println("Nothing was deleted.");
            else
                System.out.println("The following ammo: " + deletedClient.get() + " was deleted."); });
        } catch (Exception e){
            /*e.printStackTrace();*/
            System.out.println(e.getMessage());
        }
    }
    }
