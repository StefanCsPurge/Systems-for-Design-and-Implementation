package TCP;

import domain.Order;
import domain.exceptions.SocketException;
import message.Message;
import service.OrderServiceInterface;
import utils.Adapter;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class OrderMessageHandler {
    OrderServiceInterface orderService;

    public OrderMessageHandler(OrderServiceInterface orderService){
        this.orderService = orderService;
    }

    /**
     * Takes the request message from client and tries to add
     * the new order using the addOrder method from the server service
     * @param request Message from the client
     * @return Message from the server as a reply
     */
    public Message addOrder(Message request){
        Order receivedOrder = Adapter.messageToOrder(request.getBody());
        try {
            Optional<Order> opt = orderService.addOrder(receivedOrder).get();
            return opt.map(order -> new Message("ok", Adapter.orderToOIString(order)))
                    .orElseGet(() -> new Message("error", "Order could not be added"));
        }
        catch (Exception e){
            return new Message("error", e.getCause().getMessage());
        }
    }

    public Message deleteOrder(Message request){
        Long orderId = Long.valueOf(request.getBody());
        try {
            Optional<Order> opt =  orderService.delete(orderId).get();
            return opt.map(order -> new Message("ok", Adapter.orderToOIString(order)))
                    .orElseGet(() -> new Message("error", "Order does not exist"));
        }
        catch (Exception e) {
            // System.out.println(e.getCause().getMessage());
            return new Message("error", e.getCause().getMessage());
        }
    }

    public Message updateOrder(Message request){
        Order receivedOrder = Adapter.messageToOrder(request.getBody());
        try{
            orderService.update(receivedOrder);
            return new Message("ok",Adapter.orderToOIString(receivedOrder));
        }
        catch (Exception e){
            return new Message("error", e.getCause().getMessage());
        }
    }

    public Message getAllOrders(Message request){
        try {
            if(request.getBody().length() > 0)
                throw new SocketException("Bad request");
            Set<Order> result =  orderService.getAllOrders().get();
            String messageBody = result.stream()
                    .map(Adapter::orderToOIString)
                    .reduce("", (substring,element) -> substring + element + System.lineSeparator());
            return new Message("ok", messageBody);
        }
        catch (InterruptedException | ExecutionException | SocketException e) {
            // System.out.println(e.getCause().getMessage());
            return new Message("error", e.getCause().getMessage());
        }
    }
}
