package UI;

import TCP.TcpClient;
import message.Message;
import adaptor.SensorAdaptor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class Console {
    String sensor_name;
    int sensor_id;
    int lower_bound;
    int upper_bound;

    TcpClient tcpClient;
    ExecutorService executorService;

    public Console(TcpClient tcpClient, ExecutorService executorService) {
        this.tcpClient = tcpClient;
        this.executorService = executorService;
    }

    public void run(){
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.print("sensor-name: ");
            sensor_name = bufferRead.readLine();
            System.out.print("sensor-id: ");
            sensor_id = Integer.parseInt(bufferRead.readLine());
            if(sensor_id <= 1023)
                throw new Exception("sensor-id should be greater than 1023");
            System.out.print("lower_bound: ");
            lower_bound = Integer.parseInt(bufferRead.readLine());
            System.out.print("upper_bound: ");
            upper_bound = Integer.parseInt(bufferRead.readLine());
        } catch (Exception e) {
            System.out.println("error while initializing sensor");
            System.out.println(e.getMessage());
        }

        while (true) {
            try {
                Thread.sleep(new Random().nextInt(4200));
                CompletableFuture.supplyAsync(() ->{
                    Integer measurement = new Random().nextInt(upper_bound-lower_bound) + lower_bound;
                    Message message = new Message("addSensorData", SensorAdaptor.sensorDataToMessage(sensor_name,sensor_id,measurement));
                    try {
                        Message response = tcpClient.sendAndReceive(message);
                        System.out.println(response.getBody());
                        return Boolean.parseBoolean(response.getBody());
                    } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                    }
                },executorService).thenAcceptAsync(
                        response -> {

                            if(response) System.out.println("client: data sent successfully");
                                else System.out.println("client: data send failed");
                        }
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
