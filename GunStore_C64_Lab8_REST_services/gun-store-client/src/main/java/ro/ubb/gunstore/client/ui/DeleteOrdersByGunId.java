package ro.ubb.gunstore.client.ui;

import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class DeleteOrdersByGunId extends Command {
    @Autowired
    private RestTemplate restTemplate;

    public DeleteOrdersByGunId() {
        super("deleteAllByGunId", "deletes all orders by gun id");
    }

    public DeleteOrdersByGunId(String key, String desc){
        super(key, desc);
    }

    @Override
    public void execute() throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.println("gunId:");
            Long id = Long.valueOf(bufferedReader.readLine());

            restTemplate.delete("http://localhost:8080/api/storeOrders/deleteOrdersGunId/{id}", id);
            System.out.println("The orders with id " + id + " were deleted.");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
