package ro.ubb.gunstore.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.ubb.gunstore.client.ui.*;

/**
 * Created by C64.
 */
public class ClientApp {
    public static void main(String[] args) {
        System.out.println("Hello client and welcome to Commodore's 64 Gun Store! ");
        System.err.close();
        System.setErr(System.out);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                "ro.ubb.gunstore.client.config");

        Console clientConsole = context.getBean(Console.class);

        clientConsole.addCommand(context.getBean(AddGunCommand.class));
        clientConsole.addCommand(context.getBean(UpdateGunCommand.class));
        clientConsole.addCommand(context.getBean(DeleteGunCommand.class));
        clientConsole.addCommand(context.getBean(PrintGunCommand.class));

        clientConsole.addCommand(context.getBean(AddClientCommand.class));
        clientConsole.addCommand(context.getBean(UpdateClientCommand.class));
        clientConsole.addCommand(context.getBean(DeleteClientCommand.class));
        clientConsole.addCommand(context.getBean(PrintClientCommand.class));

        clientConsole.addCommand(context.getBean(AddAmmoCommand.class));
        clientConsole.addCommand(context.getBean(UpdateAmmoCommand.class));
        clientConsole.addCommand(context.getBean(DeleteAmmoCommand.class));
        clientConsole.addCommand(context.getBean(PrintAmmoCommand.class));

        clientConsole.addCommand(context.getBean(AddAccessoriesCommand.class));
        clientConsole.addCommand(context.getBean(UpdateAccessoriesCommand.class));
        clientConsole.addCommand(context.getBean(DeleteAccessoriesCommand.class));
        clientConsole.addCommand(context.getBean(PrintAccessoriesCommand.class));

        clientConsole.addCommand(context.getBean(FilterGunModelCommand.class));
        clientConsole.addCommand(context.getBean(ListGunTypeCommand.class));
        clientConsole.addCommand(context.getBean(SortGunCommand.class));
        clientConsole.addCommand(context.getBean(PrintTop3GunsCommand.class));

        clientConsole.addCommand(context.getBean(AddStoreOrderCommand.class));
        clientConsole.addCommand(context.getBean(DeleteStoreOrderCommand.class));
        clientConsole.addCommand(context.getBean(UpdateStoreOrderCommand.class));
        clientConsole.addCommand(context.getBean(PrintStoreOrderCommand.class));
        clientConsole.addCommand(context.getBean(DeleteOrdersByGunId.class));
        clientConsole.run();

        System.out.println("Client ended the process.");
    }
}
