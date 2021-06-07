package ro.ubb.gunstore.client.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ro.ubb.gunstore.core.model.StoreOrder;
import ro.ubb.gunstore.web.dto.ClientDto;
import ro.ubb.gunstore.web.dto.StoreOrderDto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

@Component
public class UpdateStoreOrderCommand extends Command {

    @Autowired
    private RestTemplate restTemplate;

    private UpdateStoreOrderCommand() { super("updateStoreOrder", "updates a store order");};

    private UpdateStoreOrderCommand(String key, String desc) {super(key, desc);};

    @Override
    public void execute() throws Exception {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("id: ");
        Long id = Long.valueOf(bufferRead.readLine());
        Optional<StoreOrderDto> optionalStoreOrder = AddStoreOrderCommand.readOrder();
        optionalStoreOrder.ifPresent(storeOrder -> {
            try {
                storeOrder.setId(id);
                restTemplate.put("http://localhost:8080/api/storeOrders/{id}", storeOrder, id);

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        });
    }
}
