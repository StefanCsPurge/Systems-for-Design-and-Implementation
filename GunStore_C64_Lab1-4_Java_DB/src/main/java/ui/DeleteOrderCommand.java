package ui;

import domain.Order;
import service.OrderService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

public class DeleteOrderCommand extends Command {
    private final OrderService orderService;

    public DeleteOrderCommand(String key, String description, OrderService orderService) {
        super(key, description);
        this.orderService = orderService;
    }

    @Override
    public void execute() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.print("orderId: ");
            Long id = Long.valueOf(bufferRead.readLine());
            Optional<Order> deletedOrder = orderService.delete(id);
            if(deletedOrder.isEmpty())
                System.out.println("Nothing was deleted.");
            else
                System.out.println("The following order: " + deletedOrder.get() + " was deleted.");
        } catch (Exception e){
            /*e.printStackTrace();*/
            System.out.println(e.getMessage());
        }
    }
}
