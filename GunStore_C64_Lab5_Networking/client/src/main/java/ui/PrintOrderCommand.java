package ui;

import domain.Order;

import service.OrderServiceClient;

import java.util.Set;

public class PrintOrderCommand extends Command {
    OrderServiceClient orderService;

    public PrintOrderCommand (String key, String description, OrderServiceClient orderService) {
        super(key, description);
        this.orderService = orderService;
    }

    @Override
    public void execute() {
        orderService.getAllOrders().thenAcceptAsync(orders -> {
                    System.out.println("\n");
                    orders.forEach(System.out::println);
                }
        );
    }
}
