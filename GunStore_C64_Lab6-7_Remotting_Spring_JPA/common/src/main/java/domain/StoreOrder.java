package domain;

import javax.persistence.Entity;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
public class StoreOrder extends BaseEntity<Long> {
    Long gunId;
    Long clientId;
    public StoreOrder() {}

    public StoreOrder(Long gunId, Long clientId) {
        this.gunId = gunId;
        this.clientId = clientId;
    }

    public Long getGunId() {
        return gunId;
    }

    public Long getClientId() {
        return clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreOrder storeOrder = (StoreOrder) o;
        return gunId.equals(storeOrder.gunId) &&
                clientId.equals(storeOrder.clientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gunId, clientId);
    }

    @Override
    public String toString() {
        return "Order{" +
                "ID=" + getId() + ", " +
                "gunId=" + gunId +
                ", clientId=" + clientId +
                '}';
    }

    /**
     * Get the CSV-line representation of the specified order
     * @return a string containing the CSV representation of the order
     */
    public String getCSVString() {
        return getId() + "," + gunId + "," + clientId;
    }

    /**
     * Set entity fields with the values specified in a CSV line representation
     * @param line a CSV line representation of the order
     */
    public void readEntityCSV(String line) {
        List<String> items = Arrays.asList(line.split(","));
        this.setId(Long.valueOf(items.get(0)));
        this.gunId = Long.valueOf(items.get(1));
        this.clientId = Long.valueOf(items.get(2));
    }
}
