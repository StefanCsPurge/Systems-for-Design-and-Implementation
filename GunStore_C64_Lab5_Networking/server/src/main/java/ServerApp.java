import TCP.ClientMessageHandler;
import TCP.MessageHandler;
import TCP.TcpServer;
import domain.Client;
import domain.Gun;
import domain.Order;
import domain.validators.ClientValidator;
import domain.validators.GunValidator;
import domain.validators.OrderValidator;
import repository.JDBCRepository;
import repository.Repository;
import service.ClientService;
import service.GunService;
import service.OrderService;
import TCP.OrderMessageHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerApp {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        TcpServer server = new TcpServer(executorService);

        Repository<Long, Gun> gunRepository = new JDBCRepository<>(new GunValidator(), Gun.class , "db_credentials");
        Repository<Long, Order> orderRepository = new JDBCRepository<>(new OrderValidator(), Order.class, "db_credentials");
        Repository<Long, Client> clientRepository = new JDBCRepository<>(new ClientValidator(), Client.class, "db_credentials");


        GunService gunService = new GunService(gunRepository, orderRepository, executorService);
        ClientService clientService = new ClientService(clientRepository, orderRepository, executorService);
        OrderService orderService = new OrderService(orderRepository, gunService, clientService, executorService);

        MessageHandler messageHandler = new MessageHandler(gunService);
        ClientMessageHandler clientMessageHandler = new ClientMessageHandler(clientService);
        OrderMessageHandler orderMessageHandler = new OrderMessageHandler(orderService);

        server.addHandler("add_gun", messageHandler::addGun);
        server.addHandler("delete_gun", messageHandler::deleteGun);
        server.addHandler("update_gun", messageHandler::updateGun);
        server.addHandler("get_all_guns", messageHandler::getAllGuns);
        server.addHandler("filter_guns_model", messageHandler::filterGunsModel);
        server.addHandler("filter_guns_type", messageHandler::filterGunsType);
        server.addHandler("get_top3_guns", messageHandler::getTop3Guns);
        server.addHandler("sort_guns_price", messageHandler::sortGunsPrice);

        server.addHandler("add_client", clientMessageHandler::addClient);
        server.addHandler("delete_client", clientMessageHandler::deleteClient);
        server.addHandler("update_client", clientMessageHandler::updateClient);
        server.addHandler("get_all_clients", clientMessageHandler::getAllClients);

        server.addHandler("add_order", orderMessageHandler::addOrder);
        server.addHandler("delete_order", orderMessageHandler::deleteOrder);
        server.addHandler("update_order", orderMessageHandler::updateOrder);
        server.addHandler("get_all_orders", orderMessageHandler::getAllOrders);

        System.out.println("Server up and running!");
        server.startServer();
        executorService.shutdown();
        System.out.println("Server stopped");
    }
}
