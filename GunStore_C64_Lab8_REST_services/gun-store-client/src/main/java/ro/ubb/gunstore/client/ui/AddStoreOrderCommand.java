package ro.ubb.gunstore.client.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ro.ubb.gunstore.web.dto.StoreOrderDto;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

@Component
public class AddStoreOrderCommand extends Command {
    @Autowired
    private RestTemplate restTemplate;

    public AddStoreOrderCommand() {super("addStoreOrder", "adds a store order in the repository" );};

    public AddStoreOrderCommand(String key, String description) { super(key, description);};

    protected static Optional<StoreOrderDto> readOrder(){
        System.out.println("Read StoreOrder {id (auto-generated), clientId, gunId}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("gunId: ");
            Long gunId = Long.valueOf(bufferRead.readLine());

            System.out.print("clientId: ");
            Long clientId = Long.valueOf(bufferRead.readLine());

            System.out.println("you've entered " + gunId + " and " + clientId);

            return Optional.of(new StoreOrderDto(gunId, clientId));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public void execute() {
        Optional<StoreOrderDto> storeOrderDtoOptional = readOrder();
        storeOrderDtoOptional.ifPresent(
                storeOrderDto -> {
                    StoreOrderDto savedStoreOrder = restTemplate.postForObject(
                            "http://localhost:8080/api/storeOrders",
                            storeOrderDto,
                            StoreOrderDto.class
                    );
                    if(savedStoreOrder == null)
                        System.out.println("Error adding store order. Order could not be added.");
                    else if(savedStoreOrder.getId() == null)
                        System.out.println("The id of the saved gun is null.");
                }
        );
    }
}