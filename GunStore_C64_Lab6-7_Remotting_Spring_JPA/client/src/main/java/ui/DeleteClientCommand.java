package ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.ClientServiceClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class DeleteClientCommand extends Command {
    @Autowired
    private ClientServiceClient clientService;

    public DeleteClientCommand() {
        super("deleteClient", "deletes a client from the repository");
    }

    public DeleteClientCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void execute() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.print("clientId: ");
            Long id = Long.valueOf(bufferRead.readLine());
            var deleted = clientService.delete(id);
            System.out.println("The following client: " + deleted + " was deleted.");
        } catch (Exception e){
            /*e.printStackTrace();*/
            System.out.println(e.getMessage());
        }
    }
}
