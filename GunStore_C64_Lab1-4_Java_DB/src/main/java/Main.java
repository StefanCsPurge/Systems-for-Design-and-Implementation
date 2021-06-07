import domain.*;
import domain.validators.*;
import repository.*;
import service.*;
import ui.*;

public class Main {
    public static void main (String[] args){
        System.out.println("Hello and welcome Commodore's 64 Gun Store! Don't forget to check the DB credentials in the db_credentials file.");

        Validator<Gun> gunValidator = new GunValidator();
        Validator<Order> orderValidator = new OrderValidator();
        Validator<Client> clientValidator = new ClientValidator();
        Validator<GunAccessories> accessoriesValidator = new AccessoryValidator();
        Validator<Ammunition> ammoValidator = new AmmoValidator();

        Repository<Long, Gun> gunRepository = new JDBCRepository<>(gunValidator, Gun.class , "db_credentials");
        Repository<Long, Ammunition> ammoRepository = new JDBCRepository<>(ammoValidator, Ammunition.class , "db_credentials");
        Repository<Long, Client> clientRepository = new JDBCRepository<>(clientValidator, Client.class, "db_credentials");
        Repository<Long, Order> orderRepository = new JDBCRepository<>(orderValidator, Order.class, "db_credentials");
        Repository<Long, GunAccessories> accessoriesRepository = new JDBCRepository<>(accessoriesValidator, GunAccessories.class, "db_credentials");

        //Repository<Long, Gun> gunRepository = new XmlFileRepository<>(gunValidator, Gun.class , "./data/guns.xml");
        //Repository<Long, Ammunition> ammoRepository = new XmlFileRepository<>(ammoValidator, Ammunition.class , "./data/ammo.xml");
        //Repository<Long, Client> clientRepository = new XmlFileRepository<>(clientValidator, Client.class, "./data/clients.xml");
        //Repository<Long, Order> orderRepository = new XmlFileRepository<>(orderValidator, Order.class, "./data/orders.xml");
        //Repository<Long, GunAccessories> accessoriesRepository = new XmlFileRepository<>(accessoriesValidator, GunAccessories.class, "./data/accessories.xml");

        // Repository<Long, Gun> gunRepository = new TxtFileRepository<>(gunValidator, Gun.class , "./data/guns");
        // Repository<Long, Ammunition> ammoRepository = new TxtFileRepository<>(ammoValidator, Ammunition.class , "./data/ammo");
        // Repository<Long, Client> clientRepository = new TxtFileRepository<>(clientValidator, Client.class, "./data/clients");
        // Repository<Long, Order> orderRepository = new TxtFileRepository<>(orderValidator, Order.class, "./data/orders");
        // Repository<Long, GunAccessories> accessoriesRepository = new TxtFileRepository<>(accessoriesValidator, GunAccessories.class, "./data/accessories");

        GunService gunService = new GunService(gunRepository, orderRepository);
        ClientService clientService = new ClientService(clientRepository, orderRepository);
        OrderService orderService = new OrderService(orderRepository, gunService, clientService);
        AccessoryService accessoryService = new AccessoryService(accessoriesRepository);
        AmmoService ammoService = new AmmoService(ammoRepository);

        Console console = new Console();
        
        console.addCommand(new AddGunCommand("addGun", "adds a gun to the repository", gunService));
        console.addCommand(new UpdateGunCommand("updateGun", "update a specific gun", gunService));
        console.addCommand(new DeleteGunCommand("deleteGun", "deletes a gun from the repository", gunService));
        console.addCommand(new PrintGunCommand("printGuns", "prints all the guns", gunService));

        console.addCommand(new AddAmmoCommand("addAmmo", "adds ammunition to the repository", ammoService));
        console.addCommand(new UpdateAmmoCommand("updateAmmo", "update a specific ammunition", ammoService));
        console.addCommand(new DeleteAmmoCommand("deleteAmmo", "deletes an ammunition from the repository", ammoService));
        console.addCommand(new PrintAmmoCommand("printAmmo", "prints all the ammunition", ammoService));

        console.addCommand(new AddClientCommand("addClient", "adds a client to the repository", clientService));
        console.addCommand(new UpdateClientCommand("updateClient", "update a specific client", clientService));
        console.addCommand(new DeleteClientCommand("deleteClient", "deletes a client from the repository", clientService));
        console.addCommand(new PrintClientCommand("printClients", "prints all the clients", clientService));

        console.addCommand(new AddOrderCommand("addOrder", "adds a order to the repository", orderService));
        console.addCommand(new UpdateOrderCommand("updateOrder", "update a specific order", orderService));
        console.addCommand(new DeleteOrderCommand("deleteOrder", "deletes a order from the repository", orderService));
        console.addCommand(new PrintOrderCommand("printOrders", "prints all the orders", orderService));

        console.addCommand(new AddAccessoryCommand("addAccessory", "adds a new accessory to the repository", accessoryService));
        console.addCommand(new DeleteAccessoryCommand("deleteAccessory", "deletes an accessory from the repository",accessoryService));
        console.addCommand(new PrintAccessoryCommand("printAccessories","prints all accessories",accessoryService));
        console.addCommand(new UpdateAccessoryCommand("updateAccessory", "update a specific accessory",accessoryService));

        console.addCommand(new FilterGunCommand("filterGunsModel", "shows the guns with the specified model", gunService));
        console.addCommand(new SortGunCommand("sortGunsPrice", "show the guns sorted ascending by price", gunService));

        console.addCommand(new DeleteByGunIdCommand("deleteOrderByGunId", "delete all orders for a given gun id.", orderService));
        console.addCommand(new ListGunTypeCommand("listGunsTypeStr", "list all guns whose type contains a given string", gunService));
        console.addCommand(new FilterAccesoriesCommand("filterByPrice", "shows the accessory with specific price", accessoryService));
        console.addCommand(new DeleteClientsStartCnp("deleteClientsCnp", "deletes all the clients that have cnp starting with", clientService));
        console.addCommand(new PrintTop3GunsCommand("printTop3Guns", "prints the top 3 of the most sold guns", gunService));

        console.run();
        
        //Attendance for lab4 18 march MINA PATRICIU DANIEL
        //Attendance for lab4 18 march MOS DANIELE
    }
}
