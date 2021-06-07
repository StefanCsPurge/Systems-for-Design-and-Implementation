package domain;

import javax.persistence.Entity;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Gun accessories object.
 */
@Entity
public class GunAccessories extends BaseEntity<Long>{
    private String name;
    private String type;
    private String company;
    private float price;

    public GunAccessories() {}
    /**
     *
     * @param name What is the accessory
     * @param type The type of the accessory
     * @param company The company that produces that accessory
     * @param price The price of the accessory
     */
    public GunAccessories(String name, String type, String company, float price) {
        this.name = name;
        this.type = type;
        this.company = company;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GunAccessories that = (GunAccessories) o;
        return Float.compare(that.price, price) == 0 && name.equals(that.name) && type.equals(that.type) && company.equals(that.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, company, price);
    }

    @Override
    public String toString() {
        return "GunAccessories{" +
                "ID='" + getId() + '\'' +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", company='" + company + '\'' +
                ", price=" + price +
                '}';
    }


    /**
     * Get the CSV-line representation of the specified gun accessory
     * @return a string containing the CSV representation of the gun accessory
     */
    public String getCSVString() {
        return getId() + "," + name + "," + type + "," + company + "," + price;
    }


    /**
     * Set entity fields with the values specified in a CSV line representation
     * @param line a CSV line representation of the gun accessory
     */
    public void readEntityCSV(String line) {
        List<String> items = Arrays.asList(line.split(","));
        this.setId(Long.valueOf(items.get(0)));
        this.name = items.get(1);
        this.type = items.get(2);
        this.company = items.get(3);
        this.price = Float.parseFloat(items.get(4));
    }
}
