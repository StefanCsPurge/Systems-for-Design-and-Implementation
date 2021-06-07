package domain;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Order extends BaseEntity<Long> {
    Long gunId;
    Long clientId;
    public Order() {}

    public Order(Long id, Long gunId, Long clientId) {
        this.setId(id);
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
        Order order = (Order) o;
        return getId().equals(order.getId()) &&
                gunId.equals(order.gunId) &&
                clientId.equals(order.clientId);
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
    @Override
    public String getCSVString() {
        return getId() + "," + gunId + "," + clientId;
    }

    /**
     * Set entity fields with the values specified in a CSV line representation
     * @param line a CSV line representation of the order
     */
    @Override
    public void readEntityCSV(String line) {
        List<String> items = Arrays.asList(line.split(","));
        this.setId(Long.valueOf(items.get(0)));
        this.gunId = Long.valueOf(items.get(1));
        this.clientId = Long.valueOf(items.get(2));
    }

    /**
     * Set entity fields with the vales specified in an XML node representation
     * @param element an node in the XML representation
     */
    @Override
    public void getEntityFromNode(Element element){
        this.setId(Long.valueOf(element.getAttribute("id")));
        this.gunId = Long.valueOf(element.getElementsByTagName("gunId").item(0).getTextContent());
        this.clientId = Long.valueOf(element.getElementsByTagName("clientId").item(0).getTextContent());
    }

    /**
     * Add an XML node to the document with the attributes of the specific orders
     * @param document an XML document
     * @return return an element in the XML document representing an order
     */
    @Override
    public Node getNodeFromEntity(Document document) {
        Element entityElem = document.createElement("order");
        entityElem.setAttribute("id", getId().toString());
        BaseEntity.addChildWithTextContent(document, entityElem, "gunId", String.valueOf(gunId));
        BaseEntity.addChildWithTextContent(document, entityElem, "clientId", String.valueOf(clientId));
        return entityElem;
    }

    /**
     * Get the sql specific queries for the specified entity (CRUD operations)
     * @return map of CRUD operations and Client specific queries for the order
     */
    @Override
    public Map<String,String> getSqlStmts(){
        return Stream.of(new String[][] {
                { "selectOne", "select * from orders where id = ?" },
                { "selectAll", "select * from orders" },
                { "insert", "insert into orders  (id, \"gunId\", \"clientId\") values (?, ?, ?)" },
                { "update", "update orders set \"gunId\"=?, \"clientId\"=? where id=?" },
                { "delete", "delete from orders where id = ?" },
        }).collect(Collectors.toMap(data -> data[0], data -> data[1]));
    }

    /**
     * Reads an entity from an PostgreSQL result set
     * @param rs sql result set
     * @throws SQLException result set does not contain specified strings
     */
    @Override
    public void readEntityFromResultSet(ResultSet rs) throws SQLException {
        this.setId(rs.getLong("id"));
        this.clientId = rs.getLong("clientId");
        this.gunId = rs.getLong("gunId");
    }

    /**
     * Prepares parameters for sql statements
     * @param ps prepared statement
     * @param stmtName statement name
     * @throws SQLException exception is raised if parameters cannot be set
     */
    @Override
    public void setSqlPrepStmtParameters(PreparedStatement ps, String stmtName) throws SQLException {
        switch (stmtName) {
            case "selectOne", "delete" -> ps.setLong(1, getId());
            case "insert" -> {
                ps.setLong(1, getId());
                ps.setLong(2, gunId);
                ps.setLong(3, clientId);
            }
            case "update" -> {
                ps.setLong(1, gunId);
                ps.setLong(2, clientId);
                ps.setLong(3, getId());
            }
        }
    }
}
