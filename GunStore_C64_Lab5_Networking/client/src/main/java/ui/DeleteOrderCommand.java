package ui;

import domain.Order;
import service.OrderServiceClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

public class DeleteOrderCommand extends Command {
    private final OrderServiceClient orderService;

    public DeleteOrderCommand(String key, String description, OrderServiceClient orderService) {
        super(key, description);
        this.orderService = orderService;
    }

    @Override
    public void execute() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.print("orderId: ");
            Long id = Long.valueOf(bufferRead.readLine());

            orderService.delete(id).thenAcceptAsync(deletedOrder ->
            {
                System.out.println("\n");
                if (deletedOrder.isEmpty())
                    System.out.println("Nothing was deleted.");
                else
                    System.out.println("The following order: " + deletedOrder.get() + " was deleted.");
            });
        } catch (Exception e){
            /*e.printStackTrace();*/
            System.out.println(e.getMessage());
        }
    }
}
