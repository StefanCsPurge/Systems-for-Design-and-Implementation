import TCP.MessageHandler;
import TCP.TcpServer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerApp {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        TcpServer server = new TcpServer(executorService);

        MessageHandler messageHandler = new MessageHandler();
        server.addHandler("addSensorData", messageHandler::addSensorData);

        System.out.println("Server up and running!");
        server.startServer();
        executorService.shutdown();
        System.out.println("Server stopped");

    }
}
