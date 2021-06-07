package ui;

import domain.Order;
import service.OrderService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

public class DeleteByGunIdCommand extends Command {
    private final OrderService orderService;

    public DeleteByGunIdCommand(String key, String description, OrderService orderService) {
        super(key, description);
        this.orderService = orderService;
    }

    @Override
    public void execute() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.print("gunId: ");
            Long id = Long.valueOf(bufferRead.readLine());
            orderService.deleteAllByGunId(id);

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
