package ui;

import domain.StoreOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.OrderServiceClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

@Component
public class AddOrderCommand extends Command {
    @Autowired
    private OrderServiceClient orderService;

    public AddOrderCommand () {
        super("addOrder", "adds a order to the repository");
    }

    public AddOrderCommand (String key, String description) {
        super(key, description);
    }

    protected static Optional<StoreOrder> readOrder(){
        System.out.println("Read Order {id(auto-generated), clientId, gunId}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("gunId: ");
            Long gunId = Long.valueOf(bufferRead.readLine());

            System.out.print("clientId: ");
            Long clientId = Long.valueOf(bufferRead.readLine());

            return Optional.of(new StoreOrder(gunId, clientId));

        }
        catch (Exception e){
            /*e.printStackTrace();*/
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public void execute() {
        Optional<StoreOrder> order = readOrder();
        order.ifPresent((x) -> {
            try {
                orderService.addOrder(x);
            } catch (Exception e) {
                /*e.printStackTrace();*/
                System.out.println(e.getMessage());
            }
        });
    }
}