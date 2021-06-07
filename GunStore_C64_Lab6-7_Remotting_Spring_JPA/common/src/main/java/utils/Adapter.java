package utils;

import domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Adapter {


    public static Map<String,String> getSqlStmts(Class<?> c) {
        if (c.equals(Gun.class))
            return Stream.of(new String[][] {
                    { "selectOne", "select * from guns where id = ?" },
                    { "selectAll", "select * from guns" },
                    { "insert", "insert into guns  (id, model, manufacturer, type, weight, price) values (?, ?, ?, ?, ?, ?)" },
                    { "update", "update guns set model=?, manufacturer=?, type=?, weight=?, price=? where id=?" },
                    { "delete", "delete from guns where id = ?" },
            }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

        if (c.equals(StoreOrder.class))
            return Stream.of(new String[][] {
                    { "selectOne", "select * from orders where id = ?" },
                    { "selectAll", "select * from orders" },
                    { "insert", "insert into orders  (id, \"gunId\", \"clientId\") values (?, ?, ?)" },
                    { "update", "update orders set \"gunId\"=?, \"clientId\"=? where id=?" },
                    { "delete", "delete from orders where id = ?" },
            }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

        if (c.equals(Client.class))
            return Stream.of(new String[][] {
                    { "selectOne", "select * from clients where id = ?" },
                    { "selectAll", "select * from clients" },
                    { "insert", "insert into clients  (id, name, cnp, budget) values (?, ?, ?, ?)" },
                    { "update", "update clients set name=?, cnp=?, budget=? where id=?" },
                    { "delete", "delete from clients where id = ?" },
            }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

        if (c.equals(GunAccessories.class))
            return Stream.of(new String[][] {
                    { "selectOne", "select * from gunaccessories where id = ?" },
                    { "selectAll", "select * from gunaccessories" },
                    { "insert", "insert into gunaccessories  (id, name, type, company, price) values (?, ?, ?, ?, ?)" },
                    { "update", "update gunaccessories set name=?, type=?, company=?, price=? where id=?" },
                    { "delete", "delete from gunaccessories where id = ?" },
            }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

        return Stream.of(new String[][] {
                { "selectOne", "select * from ammunition where id = ?" },
                { "selectAll", "select * from ammunition" },
                { "insert", "insert into ammunition  (id, \"noOfRounds\", caliber, manufacturer, price) values (?, ?, ?, ?, ?)" },
                { "update", "update ammunition set \"noOfRounds\"=?, caliber=?, manufacturer=?, price=? where id=?" },
                { "delete", "delete from ammunition where id = ?" },
        }).collect(Collectors.toMap(data -> data[0], data -> data[1]));
    }


    /*public static BaseEntity<?> getEntityFromResultSet(ResultSet rs, Class<?> c) throws SQLException {
        if (c.equals(Gun.class)){
            var id = rs.getLong("id");
            var model = rs.getString("model");
            var manufacturer = rs.getString("manufacturer");
            var type = rs.getString("type");
            var weight = rs.getFloat("weight");
            var price = rs.getFloat("price");
            return new Gun(id,model,manufacturer,type,weight,price);
        }
        if (c.equals(Order.class)){
            var id = rs.getLong("id");
            var clientId = rs.getLong("clientId");
            var gunId = rs.getLong("gunId");
            return new Order(id,gunId,clientId);
        }
        if (c.equals(Client.class)){
            var id = rs.getLong("id");
            var name = rs.getString("name");
            var cnp = rs.getString("cnp");
            var budget = rs.getFloat("budget");
            return new Client(id,name,cnp,budget);
        }
        if(c.equals(Ammunition.class)){
            var id = rs.getLong("id");
            var noOfRounds = rs.getInt("noOfRounds");
            var caliber =  rs.getFloat("caliber");
            var manufacturer = rs.getString("manufacturer");
            var price = rs.getFloat("price");
            return new Ammunition(id,noOfRounds,caliber,manufacturer,price);
        }
        if(c.equals(GunAccessories.class)){
            var id = rs.getLong("id");
            var name = rs.getString("name");
            var type =  rs.getString("type");
            var company = rs.getString("company");
            var price = rs.getFloat("price");
            return new GunAccessories(id,name,type,company,price);
        }
        return new BaseEntity<>();
    }*/

    /**
     * Prepares parameters for sql statements
     * @param operation statement identifier
     * @param entity domain entity
     */
    public static<T> Object[] getSqlStmtParams(String operation, T entity) {
        List<Object> fields =  new ArrayList<>();
        if(entity instanceof Gun)
            fields = getGunFields(operation, (Gun) entity);
        else if (entity instanceof StoreOrder)
            fields = getOrderFields(operation, (StoreOrder) entity);
        else if (entity instanceof Client)
            fields = getClientFields(operation, (Client) entity);
         else if (entity instanceof Ammunition)
            fields = getAmmoFields(operation, (Ammunition) entity);
         else if (entity instanceof GunAccessories)
            fields = getGunAccessoryFields(operation, (GunAccessories) entity);
        return fields.toArray();
    }

    private static List<Object> getGunAccessoryFields(String operation, GunAccessories accessory) {
        List<Object> fields =  new ArrayList<>();
        switch (operation) {
            case "selectOne", "delete" -> fields.add(accessory.getId());
            case "insert" -> {
                fields.add(accessory.getId());
                fields.add(accessory.getName());
                fields.add(accessory.getType());
                fields.add(accessory.getCompany());
                fields.add(accessory.getPrice());
            }
            case "update" -> {
                fields.add(accessory.getName());
                fields.add(accessory.getType());
                fields.add(accessory.getCompany());
                fields.add(accessory.getPrice());
                fields.add(accessory.getId());
            }
        }
        return fields;
    }

    private static List<Object> getAmmoFields(String operation, Ammunition ammo) {
        List<Object> fields =  new ArrayList<>();
        switch (operation) {
            case "selectOne", "delete" -> fields.add(ammo.getId());
            case "insert" -> {
                fields.add(ammo.getId());
                fields.add(ammo.getNoOfRounds());
                fields.add(ammo.getCaliber());
                fields.add(ammo.getManufacturer());
                fields.add(ammo.getPrice());
            }
            case "update" -> {
                fields.add(ammo.getNoOfRounds());
                fields.add(ammo.getCaliber());
                fields.add(ammo.getManufacturer());
                fields.add(ammo.getPrice());
                fields.add(ammo.getId());
            }
        }
        return fields;
    }

    private static List<Object> getClientFields(String operation, Client client) {
        List<Object> fields =  new ArrayList<>();
        switch (operation) {
            case "selectOne", "delete" -> fields.add(client.getId());
            case "insert" -> {
                fields.add(client.getId());
                fields.add(client.getName());
                fields.add(client.getCnp());
                fields.add(client.getBudget());
            }
            case "update" -> {
                fields.add(client.getName());
                fields.add(client.getCnp());
                fields.add(client.getBudget());
                fields.add(client.getId());
            }
        }
        return fields;
    }

    private static List<Object> getOrderFields(String operation, StoreOrder storeOrder) {
        List<Object> fields =  new ArrayList<>();
        switch (operation) {
            case "selectOne", "delete" -> fields.add(storeOrder.getId());
            case "insert" -> {
                fields.add(storeOrder.getId());
                fields.add(storeOrder.getGunId());
                fields.add(storeOrder.getClientId());
            }
            case "update" -> {
                fields.add(storeOrder.getGunId());
                fields.add(storeOrder.getClientId());
                fields.add(storeOrder.getId());
            }
        }
        return fields;
    }

    public static List<Object> getGunFields(String operation, Gun gun) {
        List<Object> fields =  new ArrayList<>();
        switch (operation) {
            case "selectOne", "delete" -> fields.add(gun.getId());
            case "insert" -> {
                fields.add(gun.getId());
                fields.add(gun.getModel());
                fields.add(gun.getManufacturer());
                fields.add(gun.getType());
                fields.add(gun.getWeight());
                fields.add(gun.getPrice());
            }
            case "update" -> {
                fields.add(gun.getModel());
                fields.add(gun.getManufacturer());
                fields.add(gun.getType());
                fields.add(gun.getWeight());
                fields.add(gun.getPrice());
                fields.add(gun.getId());
            }
        }
        return fields;
    }


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

    /*public static Gun messageToGun(String message){
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
        var noOfRounds = Integer.parseInt(items.get(1));
        var caliber = Float.parseFloat(items.get(2));
        var manufacturer = items.get(3);
        var price = Float.parseFloat(items.get(4));

        return new Ammunition(id, noOfRounds, caliber, manufacturer, price);
    }*/

    public static String ammoToOIString(Ammunition ammo) {
        return ammo.getId() + "," + ammo.getNoOfRounds() + "," + ammo.getCaliber() + "," + ammo.getManufacturer() + "," + ammo.getPrice();
    }
}
