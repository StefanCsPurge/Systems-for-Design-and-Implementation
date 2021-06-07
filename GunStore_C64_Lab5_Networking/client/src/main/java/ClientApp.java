import TCP.TcpClient;
import service.*;
import ui.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientApp {
    public static void main(String[] args) {
        System.out.println("Hello client and welcome to Commodore's 64 Gun Store! " +
                "Don't forget to check the DB credentials in the db_credentials file.");

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        TcpClient tcpClient = new TcpClient();

        GunServiceClient gunService = new GunServiceClient(executorService, tcpClient);
        ClientServiceClient clientService = new ClientServiceClient(executorService, tcpClient);
        OrderServiceClient orderService = new OrderServiceClient(executorService, tcpClient);
        AmmoServiceClient ammunitionService = new AmmoServiceClient(executorService, tcpClient);

        // ExecutorService executorServiceUi = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        Console ui = new Console();
        ui.addCommand(new AddGunCommand("addGun", "adds a gun to the repository", gunService));
        ui.addCommand(new UpdateGunCommand("updateGun", "update a specific gun", gunService));
        ui.addCommand(new DeleteGunCommand("deleteGun", "deletes a gun from the repository", gunService));
        ui.addCommand(new PrintGunCommand("printGuns", "prints all the guns", gunService));

        ui.addCommand(new AddClientCommand("addClient", "adds a client to the repository", clientService));
        ui.addCommand(new UpdateClientCommand("updateClient", "update a specific client", clientService));
        ui.addCommand(new DeleteClientCommand("deleteClient", "deletes a client from the repository", clientService));
        ui.addCommand(new PrintClientCommand("printClients", "prints all the clients", clientService));

        ui.addCommand(new AddOrderCommand("addOrder", "adds a order to the repository", orderService));
        ui.addCommand(new UpdateOrderCommand("updateOrder", "update a specific order", orderService));
        ui.addCommand(new DeleteOrderCommand("deleteOrder", "deletes a order from the repository", orderService));
        ui.addCommand(new PrintOrderCommand("printOrders", "prints all the orders", orderService));

        ui.addCommand(new AddAmmoCommand("addAmmo", "adds ammo to the repository", ammunitionService));
        ui.addCommand(new UpdateAmmoCommand("updateAmmo", "update a specific ammunition", ammunitionService));
        ui.addCommand(new DeleteAmmoCommand("deleteAmmo", "deletes ammunition from the repository", ammunitionService));
        ui.addCommand(new PrintAmmoCommand("printAmmo", "prints all ammunition", ammunitionService));

        ui.addCommand(new FilterGunCommand("filterGunsModel", "shows the guns with the specified model", gunService));
        ui.addCommand(new SortGunCommand("sortGunsPrice", "show the guns sorted ascending by price", gunService));
        ui.addCommand(new ListGunTypeCommand("listGunsTypeStr", "list all guns whose type contains a given string", gunService));
        ui.addCommand(new PrintTop3GunsCommand("printTop3Guns", "prints the top 3 of the most sold guns", gunService));

        


        ui.run();

        executorService.shutdown();
        // executorServiceUi.shutdown();
        System.out.println("Client ended the process.");
    }
}
