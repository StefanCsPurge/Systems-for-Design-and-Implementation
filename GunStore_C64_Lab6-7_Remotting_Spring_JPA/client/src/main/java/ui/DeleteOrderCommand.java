package ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.OrderServiceClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class DeleteOrderCommand extends Command {
    @Autowired
    private OrderServiceClient orderService;

    public DeleteOrderCommand() {
        super("deleteOrder", "deletes a order from the repository");
    }

    public DeleteOrderCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void execute() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.print("orderId: ");
            Long id = Long.valueOf(bufferRead.readLine());

            var deleted = orderService.delete(id);
            System.out.println("\n");
            System.out.println("The following order: " + deleted + " was deleted.");

        } catch (Exception e){
            /*e.printStackTrace();*/
            System.out.println(e.getMessage());
        }
    }
}
