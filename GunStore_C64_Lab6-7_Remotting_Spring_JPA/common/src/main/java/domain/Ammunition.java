package domain;

import javax.persistence.Entity;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
public class Ammunition extends BaseEntity<Long> {
    private int noOfRounds;
    private float caliber;
    private String manufacturer;
    private float price;

    public Ammunition() {}

    public Ammunition(int noOfRounds, float caliber, String manufacturer, float price) {
        this.noOfRounds = noOfRounds;
        this.caliber = caliber;
        this.manufacturer = manufacturer;
        this.price = price;
    }

    public int getNoOfRounds() {
        return noOfRounds;
    }

    public float getCaliber() {
        return caliber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ammunition ammo = (Ammunition) o;
        return noOfRounds == ammo.noOfRounds &&
                caliber == ammo.caliber &&
                manufacturer.equals(ammo.manufacturer) &&
                price == ammo.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(noOfRounds, caliber, manufacturer, price);
    }

    @Override
    public String toString() {
        return "Ammo{" +
                "ID=" + getId() +
                ", noOfRounds=" + noOfRounds +
                ", caliber=" + caliber +
                ", manufacturer=" + manufacturer +
                ", price=" + price +
                '}';
    }

    /**
     * Get the CSV-line representation of the specified ammunition
     * @return a string containing the CSV representation of the ammunition
     */
    public String getCSVString() {
        return getId() + "," + noOfRounds + "," + caliber + "," + manufacturer + "," + price;
    }

    /**
     * Set entity fields with the values specified in a CSV line representation
     * @param line a CSV line representation of the ammunition
     */
    public void readEntityCSV(String line) {
        List<String> items = Arrays.asList(line.split(","));
        this.setId(Long.valueOf(items.get(0)));
        this.noOfRounds = Integer.parseInt(items.get(1));
        this.caliber = Float.parseFloat(items.get(2));
        this.manufacturer = items.get(3);
        this.price = Float.parseFloat(items.get(4));
    }

}
