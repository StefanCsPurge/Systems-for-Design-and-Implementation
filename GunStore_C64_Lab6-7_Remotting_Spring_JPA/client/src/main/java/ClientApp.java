
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ui.*;


public class ClientApp {
    public static void main(String[] args) {
        System.out.println("Hello client and welcome to Commodore's 64 Gun Store! ");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("config");

        Console clientConsole = context.getBean(Console.class);

        clientConsole.addCommand(context.getBean(AddGunCommand.class));
        clientConsole.addCommand(context.getBean(UpdateGunCommand.class));
        clientConsole.addCommand(context.getBean(DeleteGunCommand.class));
        clientConsole.addCommand(context.getBean(PrintGunCommand.class));

        clientConsole.addCommand(context.getBean(AddClientCommand.class));
        clientConsole.addCommand(context.getBean(UpdateClientCommand.class));
        clientConsole.addCommand(context.getBean(DeleteClientCommand.class));
        clientConsole.addCommand(context.getBean(PrintClientCommand.class));

        clientConsole.addCommand(context.getBean(AddOrderCommand.class));
        clientConsole.addCommand(context.getBean(UpdateOrderCommand.class));
        clientConsole.addCommand(context.getBean(DeleteOrderCommand.class));
        clientConsole.addCommand(context.getBean(PrintOrderCommand.class));

        clientConsole.addCommand(context.getBean(AddAmmoCommand.class));
        clientConsole.addCommand(context.getBean(UpdateAmmoCommand.class));
        clientConsole.addCommand(context.getBean(DeleteAmmoCommand.class));
        clientConsole.addCommand(context.getBean(PrintAmmoCommand.class));

        clientConsole.addCommand(context.getBean(FilterGunCommand.class));
        clientConsole.addCommand(context.getBean(SortGunCommand.class));
        clientConsole.addCommand(context.getBean(ListGunTypeCommand.class));
        clientConsole.addCommand(context.getBean(PrintTop3GunsCommand.class));

        clientConsole.addCommand(context.getBean(AddAccessoryCommand.class));
        clientConsole.addCommand(context.getBean(DeleteAccessoryCommand.class));
        clientConsole.addCommand(context.getBean(UpdateAccessoryCommand.class));
        clientConsole.addCommand(context.getBean(PrintAccessoryCommand.class));

        clientConsole.run();

        System.out.println("Client ended the process.");
    }
}
