package domain;

import javax.persistence.Entity;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


/**
 * Object representing a client.
 */
@Entity
public class Client  extends BaseEntity<Long>{
    private String name;
    private String cnp;
    private float budget;

    public Client(){}
    /**
     * Creates a new client.
     * @param name The name, surname and middle name of the client as one string.
     * @param cnp The Personal Identification Number as string.
     * @param budget The client's budget for a store purchase.
     */
    public Client(String name, String cnp, float budget) {
        this.name = name;
        this.cnp = cnp;
        this.budget = budget;
    }

    /**
     * Get the name of the client
     * @return client's name
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Float.compare(client.budget, budget) == 0 && name.equals(client.name) && cnp.equals(client.cnp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cnp, budget);
    }

    @Override
    public String toString() {
        return "Client{" +
                "ID=" + getId() + ", " +
                "name='" + name + '\'' +
                ", cnp='" + cnp + '\'' +
                ", budget=" + budget +
                '}';
    }


    /**
     * Get the CSV-line representation of the specified client
     * @return a string containing the CSV representation of the client
     */
    public String getCSVString() {
        return getId() + "," + name + "," + cnp + "," + budget;
    }


    /**
     * Set entity fields with the values specified in a CSV line representation
     * @param line a CSV line representation of the client
     */
    public void readEntityCSV(String line) {
        List<String> items = Arrays.asList(line.split(","));
        this.setId(Long.valueOf(items.get(0)));
        this.name = items.get(1);
        this.cnp = items.get(2);
        this.budget = Float.parseFloat(items.get(3));
    }
}
