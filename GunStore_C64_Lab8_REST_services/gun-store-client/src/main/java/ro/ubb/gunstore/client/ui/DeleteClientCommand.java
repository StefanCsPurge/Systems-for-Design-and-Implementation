package ro.ubb.gunstore.client.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class DeleteClientCommand extends Command {
    @Autowired
    private RestTemplate restTemplate;

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

            restTemplate.delete("http://localhost:8080/api/clients/{id}", id);

            System.out.println("The client with ID " + id + " was deleted.");

        } catch (Exception e){
            /*e.printStackTrace();*/
            System.out.println(e.getMessage());
        }
    }
}
