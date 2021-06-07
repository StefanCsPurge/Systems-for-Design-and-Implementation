package ui;

import domain.Order;
import service.OrderServiceClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

public class AddOrderCommand extends Command {
    OrderServiceClient orderService;

    public AddOrderCommand (String key, String description, OrderServiceClient orderService) {
        super(key, description);
        this.orderService = orderService;

    }

    protected static Optional<Order> readOrder(){
        System.out.println("Read Order {id, clientId, gunId}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("id: ");
            Long id = Long.valueOf(bufferRead.readLine());

            System.out.print("gunId: ");
            Long gunId = Long.valueOf(bufferRead.readLine());

            System.out.print("clientId: ");
            Long clientId = Long.valueOf(bufferRead.readLine());

            return Optional.of(new Order(id, gunId, clientId));

        }
        catch (Exception e){
            /*e.printStackTrace();*/
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public void execute() {
        Optional<Order> order = readOrder();
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