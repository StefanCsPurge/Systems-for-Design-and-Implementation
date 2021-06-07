package ui;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.OrderServiceClient;

@Component
public class PrintOrderCommand extends Command {
    @Autowired
    private OrderServiceClient orderService;

    public PrintOrderCommand () {
        super("printOrders", "prints all the orders");
    }

    public PrintOrderCommand (String key, String description) {
        super(key, description);
    }

    @Override
    public void execute() {
        orderService.getAllOrders().forEach(System.out::println);
    }
}
