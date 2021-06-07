package ui;

import domain.GunAccessories;
import service.AccessoryService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

public class DeleteAccessoryCommand extends Command {
    private final AccessoryService accessoryService;

    public DeleteAccessoryCommand(String key, String description, AccessoryService accessoryService) {
        super(key, description);
        this.accessoryService = accessoryService;
    }

    @Override
    public void execute() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.print("gunAccessoryId: ");
            Long id = Long.valueOf(bufferRead.readLine());
            Optional<GunAccessories> deleteAcc = accessoryService.delete(id);
            if(deleteAcc.isEmpty())
                System.out.println("Nothing was deleted.");
            else
                System.out.println("The following accessory: " + deleteAcc.get() + " was deleted.");
        } catch (Exception e){
            /*e.printStackTrace();*/
            System.out.println(e.getMessage());
        }
    }
}
