package ui;

import domain.Order;
import service.OrderServiceClient;

import java.util.Optional;

public class UpdateOrderCommand extends Command {
    OrderServiceClient orderService;

    public UpdateOrderCommand(String key, String description, OrderServiceClient orderService) {
        super(key, description);
        this.orderService = orderService;
    }

    @Override
    public void execute() {
        Optional<Order> optionalOrder = AddOrderCommand.readOrder();
        optionalOrder.ifPresent((order) -> {
            try {
                orderService.update(order);
            } catch (Exception e) {
                /*e.printStackTrace();*/
                System.out.println(e.getMessage());
            }
        });
    }
}
