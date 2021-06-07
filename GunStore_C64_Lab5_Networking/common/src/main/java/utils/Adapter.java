package utils;

import domain.Ammunition;
import domain.Client;
import domain.Gun;
import domain.Order;

import java.util.Arrays;
import java.util.List;

public class Adapter {

    /**
     * Get the CSV-line representation of the specified gun
     * @return a string containing the CSV representation of the gun
     */
    public static String gunToOIString(Gun gun){
        return  gun.getId() + "," +
                gun.getModel() + "," +
                gun.getManufacturer() + "," +
                gun.getType() + "," +
                gun.getWeight() + "," +
                gun.getPrice();
    }

    public static Gun messageToGun(String message){
        String[] tokens = message.split(",");
        return new Gun(Long.parseLong(tokens[0]),tokens[1], tokens[2], tokens[3],
                Float.parseFloat(tokens[4]), Float.parseFloat(tokens[5]));
    }

    public static String clientToOIString(Client client) {
        return client.getId() + "," + client.getName() + "," + client.getCnp() + "," + client.getBudget();
    }

    public static Client messageToClient(String line) {
        List<String> items = Arrays.asList(line.split(","));
        Long id = Long.valueOf(items.get(0));
        var name =  items.get(1);
        var cnp = items.get(2);
        var budget = Float.parseFloat(items.get(3));
        return new Client(id, name, cnp, budget);
    }

    public static String orderToOIString(Order order) {
        return order.getId() + "," + order.getGunId() + "," + order.getClientId();
    }

    public static Order messageToOrder(String line){
        List<String> items = Arrays.asList(line.split(","));
        var id = Long.valueOf(items.get(0));
        var gunId = Long.valueOf(items.get(1));
        var clientId = Long.valueOf(items.get(2));
        return new Order(id, gunId, clientId);
    }

    public static Ammunition messageToAmmo(String line) {
        List<String> items = Arrays.asList(line.split(","));
        var id = Long.valueOf(items.get(0));
        var noOfRounds = Integer.valueOf(items.get(1));
        var caliber = Float.valueOf(items.get(2));
        var manufacturer = items.get(3);
        var price = Float.valueOf(items.get(4));

        return new Ammunition(id, noOfRounds, caliber, manufacturer, price);
    }

    public static String ammoToOIString(Ammunition ammo) {
        return ammo.getId() + "," + ammo.getNoOfRounds() + "," + ammo.getCaliber() + "," + ammo.getManufacturer() + "," + ammo.getPrice();
    }
}
