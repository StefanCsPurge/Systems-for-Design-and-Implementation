package ui;

import domain.Order;
import service.OrderService;

import java.util.Set;

public class PrintOrderCommand extends Command {
    OrderService orderService;

    public PrintOrderCommand (String key, String description, OrderService orderService) {
        super(key, description);
        this.orderService = orderService;
    }

    @Override
    public void execute() {
        Set<Order> orders = orderService.getAllOrders();
        orders.forEach(System.out::println);
    }
}
