package ro.ubb.gunstore.client.ui;

import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class DeleteStoreOrderCommand extends Command {
    @Autowired
    private RestTemplate restTemplate;

    public DeleteStoreOrderCommand() {
        super("deleteStoreOrder", "deletes a store order");
    }

    public DeleteStoreOrderCommand(String key, String desc){
        super(key, desc);
    }

    @Override
    public void execute() throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.println("storeOrderId:");
            Long id = Long.valueOf(bufferedReader.readLine());

            restTemplate.delete("http://localhost:8080/api/storeOrders/{id}", id);
            System.out.println("The client with id " + id + " was deleted.");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
