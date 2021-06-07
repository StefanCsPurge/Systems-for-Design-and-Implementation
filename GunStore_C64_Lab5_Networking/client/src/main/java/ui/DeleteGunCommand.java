package ui;

import service.GunServiceClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DeleteGunCommand extends Command {
    private final GunServiceClient gunService;

    public DeleteGunCommand(String key, String description, GunServiceClient gunService) {
        super(key, description);
        this.gunService = gunService;
    }

    @Override
    public void execute() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.print("gunId: ");
            Long id = Long.valueOf(bufferRead.readLine());
            gunService.delete(id).thenAcceptAsync(deletedGun ->
            {
                System.out.println("\n");
                if (deletedGun.isEmpty())
                    System.out.println("Nothing was deleted.");
                else
                    System.out.println("The following gun: " + deletedGun.get() + " was deleted.");
            });

        } catch (Exception e){
            /*e.printStackTrace();*/
            System.out.println(e.getMessage());
        }
    }
}
