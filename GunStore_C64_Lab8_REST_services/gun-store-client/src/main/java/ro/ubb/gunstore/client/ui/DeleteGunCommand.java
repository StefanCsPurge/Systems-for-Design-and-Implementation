package ro.ubb.gunstore.client.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class DeleteGunCommand extends Command {
    @Autowired
    private RestTemplate restTemplate;

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

            restTemplate.delete("http://localhost:8080/api/guns/{id}", id);

            System.out.println("The gun with ID " + id + " was deleted.");

        } catch (Exception e){
            /*e.printStackTrace();*/
            System.out.println(e.getMessage());
        }
    }
}
