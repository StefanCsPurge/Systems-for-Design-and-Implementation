package service;

import TCP.TcpClient;
import TCP.TcpClient;
import domain.Order;
import domain.Order;
import domain.exceptions.SocketException;
import message.Message;
import utils.Adapter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

public class OrderServiceClient implements OrderServiceInterface {
    private final ExecutorService executorService;
    private final TcpClient tcpClient;

    public OrderServiceClient (ExecutorService executorService, TcpClient tcpClient) {
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }
    
    @Override
    public CompletableFuture<Optional<Order>> addOrder(Order order) throws Exception {
        Message message = new Message("add_order", Adapter.orderToOIString(order));
        Message response;

        try {
            response = tcpClient.sendAndReceive(message);
        }
        catch (SocketException e) {
            throw new RuntimeException(e.getCause());
        }

        String body = response.getBody();
        String header = response.getHeader();
        if(header.equals("error"))
            throw new Exception(body);

        return CompletableFuture.supplyAsync(
                Optional::empty,
                executorService
        );
    }

    @Override
    public CompletableFuture<Optional<Order>> delete(Long aLong) throws Exception {
        Message message = new Message("delete_order", String.valueOf(aLong));
        Message response = tcpClient.sendAndReceive(message);

        String body = response.getBody();
        String header = response.getHeader();
        if (header.equals("error"))
            throw new Exception(body);

        return CompletableFuture.supplyAsync(
                ()->Optional.of(Adapter.messageToOrder(body)),
                executorService
        );
    }

    @Override
    public CompletableFuture<Optional<Order>> update(Order entity) throws Exception {
        Message message = new Message("update_order", Adapter.orderToOIString(entity));
        Message response = tcpClient.sendAndReceive(message);

        String body = response.getBody();
        String header = response.getHeader();
        if (header.equals("error"))
            throw new Exception(body);

        return CompletableFuture.supplyAsync(
                Optional::empty,
                executorService
        );
    }

    @Override
    public void deleteAllByGunId(Long gunId) {

    }

    @Override
    public CompletableFuture<Set<Order>> getAllOrders() {
        Message message = new Message("get_all_orders", "");
        Message response;
        try {
            response = tcpClient.sendAndReceive(message);
        } catch (SocketException e){
            throw new RuntimeException(e.getCause());
        }
        Set<Order> orders;

        String body = response.getBody();
        if (body.length() > 0) {
            String[] tokens = (body.split(System.lineSeparator()));
            orders = Arrays.stream(tokens).map(Adapter::messageToOrder).collect(Collectors.toSet());
        }
        else
            orders = new HashSet<>();

        return CompletableFuture.supplyAsync(
                ()-> orders,
                executorService
        );
    }
}
