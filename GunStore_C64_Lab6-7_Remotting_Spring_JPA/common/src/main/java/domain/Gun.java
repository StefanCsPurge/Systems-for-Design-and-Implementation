package domain;

import javax.persistence.Entity;
import java.util.*;


/**
 * Object representing a gun.
 */
@Entity
public class Gun extends BaseEntity<Long> {
    private String model;
    private String manufacturer;
    private String type;
    private float weight;
    private float price;
    private Long timesSold;

    public Gun(){}

    /**
     * Creates a new gun.
     * @param model The model of the gun.
     * @param manufacturer The manufacturer of the gun.
     * @param type The type of the gun. (e.g. Semi-automatic, Automatic)
     * @param weight The exact weight of the gun.
     * @param price The price of the gun.
     */

    public Gun(String model, String manufacturer, String type, float weight, float price){
        this.model = model;
        this.manufacturer = manufacturer;
        this.type = type;
        this.weight = weight;
        this.price = price;
        this.timesSold = 0L;
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getType() {
        return type;
    }

    public Long getTimesSold() {return timesSold;}

    public void setType(String type) {
        this.type = type;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setTimesSold(Long timesSold){ this.timesSold = timesSold; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gun gun = (Gun) o;
        return Float.compare(gun.weight, weight) == 0 &&
                Float.compare(gun.price, price) == 0 &&
                Objects.equals(model, gun.model) &&
                Objects.equals(manufacturer, gun.manufacturer) &&
                Objects.equals(type, gun.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, manufacturer, type, weight, price);
    }

    @Override
    public String toString() {
        return "Gun{" +
                "ID='" + this.getId() + '\'' +
                ", model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", type='" + type + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                '}';
    }


    /**
     * Set entity fields with the values specified in a CSV line representation
     * @param line a CSV line representation of the gun
     */
    public void readEntityCSV(String line){
        List<String> items = Arrays.asList(line.split(","));
        this.setId(Long.valueOf(items.get(0)));
        this.model = items.get(1);
        this.manufacturer = items.get(2);
        this.type = items.get(3);
        this.weight = Float.parseFloat(items.get(4));
        this.price = Float.parseFloat(items.get(5));
    }
}
