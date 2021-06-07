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

public class Ammunition extends BaseEntity<Long> {
    private int noOfRounds;
    private float caliber;
    private String manufacturer;
    private float price;

    public Ammunition() {
    }

    public Ammunition(Long id, int noOfRounds, float caliber, String manufacturer, float price) {
        this.setId(id);
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
    @Override
    public String getCSVString() {
        return getId() + "," + noOfRounds + "," + caliber + "," + manufacturer + "," + price;
    }

    /**
     * Set entity fields with the values specified in a CSV line representation
     * @param line a CSV line representation of the ammunition
     */
    @Override
    public void readEntityCSV(String line) {
        List<String> items = Arrays.asList(line.split(","));
        this.setId(Long.valueOf(items.get(0)));
        this.noOfRounds = Integer.parseInt(items.get(1));
        this.caliber = Float.parseFloat(items.get(2));
        this.manufacturer = items.get(3);
        this.price = Float.parseFloat(items.get(4));
    }

    /**
     * Set entity fields with the vales specified in an XML node representation
     * @param element an node in the XML representation
     */
    @Override
    public void getEntityFromNode(Element element) {
        this.setId(Long.valueOf(element.getAttribute("id")));
        this.noOfRounds = Integer.parseInt(element.getElementsByTagName("noOfRounds").item(0).getTextContent());
        this.caliber = Float.parseFloat(element.getElementsByTagName("caliber").item(0).getTextContent());
        this.manufacturer = element.getElementsByTagName("manufacturer").item(0).getTextContent();
        this.price = Float.parseFloat(element.getElementsByTagName("price").item(0).getTextContent());
    }

    /**
     * Add an XML node to the document with the attributes of the specific ammunition
     * @param document an XML document
     * @return return an element in the XML document representing ammunition
     */
    @Override
    public Node getNodeFromEntity(Document document) {
        Element entityElem = document.createElement("ammunition");
        entityElem.setAttribute("id", getId().toString());
        BaseEntity.addChildWithTextContent(document, entityElem, "noOfRounds", String.valueOf(noOfRounds));
        BaseEntity.addChildWithTextContent(document, entityElem, "caliber", String.valueOf(caliber));
        BaseEntity.addChildWithTextContent(document, entityElem, "manufacturer", String.valueOf(manufacturer));
        BaseEntity.addChildWithTextContent(document, entityElem, "price", String.valueOf(price));
        return entityElem;
    }

    /**
     * Get the sql specific queries for the specified entity (CRUD operations)
     * @return map of CRUD operations and Client specific queries for ammunition
     */

    @Override
    public Map<String,String> getSqlStmts(){
        return Stream.of(new String[][] {
                { "selectOne", "select * from ammunition where id = ?" },
                { "selectAll", "select * from ammunition" },
                { "insert", "insert into ammunition  (id, \"noOfRounds\", caliber, manufacturer, price) values (?, ?, ?, ?, ?)" },
                { "update", "update ammunition set \"noOfRounds\"=?, caliber=?, manufacturer=?, price=? where id=?" },
                { "delete", "delete from ammunition where id = ?" },
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
        this.noOfRounds = rs.getInt("noOfRounds");
        this.caliber =  rs.getFloat("caliber");
        this.manufacturer = rs.getString("manufacturer");
        this.price = rs.getFloat("price");
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
                ps.setInt(2, noOfRounds);
                ps.setFloat(3, caliber);
                ps.setString(4, manufacturer);
                ps.setFloat(5, price);
            }
            case "update" -> {
                ps.setInt(1, noOfRounds);
                ps.setFloat(2, caliber);
                ps.setString(3, manufacturer);
                ps.setFloat(4, price);
                ps.setLong(5, getId());
            }
        }
    }
}
