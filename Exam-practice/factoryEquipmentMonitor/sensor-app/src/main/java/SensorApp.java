import TCP.TcpClient;
import UI.Console;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SensorApp {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        TcpClient tcpClient = new TcpClient();

        Console console = new Console(tcpClient, executorService);
        console.run();

        System.out.println("client shut down");

        executorService.shutdown();
    }
}
