package ui;

import domain.StoreOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.OrderServiceClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

@Component
public class UpdateOrderCommand extends Command {
    @Autowired
    private OrderServiceClient orderService;

    public UpdateOrderCommand() {
        super("updateOrder", "update a specific order");
    }

    public UpdateOrderCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void execute() throws IOException {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("id: ");
        Long id = Long.valueOf(bufferRead.readLine());
        Optional<StoreOrder> optionalOrder = AddOrderCommand.readOrder();
        optionalOrder.ifPresent((order) -> {
            try {
                order.setId(id);
                orderService.update(order);
            } catch (Exception e) {
                /*e.printStackTrace();*/
                System.out.println(e.getMessage());
            }
        });
    }
}
