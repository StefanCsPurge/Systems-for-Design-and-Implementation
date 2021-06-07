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


/**
 * Object representing a gun.
 */
public class Gun extends BaseEntity<Long>{
    private String model;
    private String manufacturer;
    private String type;
    private float weight;
    private float price;

    public Gun(){
    }

    /**
     * Creates a new gun.
     * @param model The model of the gun.
     * @param manufacturer The manufacturer of the gun.
     * @param type The type of the gun. (e.g. Semi-automatic, Automatic)
     * @param weight The exact weight of the gun.
     * @param price The price of the gun.
     */

    public Gun(Long id, String model, String manufacturer, String type, float weight, float price){
        this.setId(id);
        this.model = model;
        this.manufacturer = manufacturer;
        this.type = type;
        this.weight = weight;
        this.price = price;
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



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gun gun = (Gun) o;
        return gun.getId().equals(getId()) &&
                Float.compare(gun.weight, weight) == 0 &&
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
                "ID='" + getId() + '\'' +
                ", model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", type='" + type + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                '}';
    }


    /**
     * Get the CSV-line representation of the specified gun
     * @return a string containing the CSV representation of the gun
     */
    @Override
    public String getCSVString() {
        return getId() + "," + model + "," + manufacturer + "," + type + "," + weight + "," + price;
    }


    /**
     * Set entity fields with the values specified in a CSV line representation
     * @param line a CSV line representation of the gun
     */
    @Override
    public void readEntityCSV(String line){
        List<String> items = Arrays.asList(line.split(","));
        this.setId(Long.valueOf(items.get(0)));
        this.model = items.get(1);
        this.manufacturer = items.get(2);
        this.type = items.get(3);
        this.weight = Float.parseFloat(items.get(4));
        this.price = Float.parseFloat(items.get(5));
    }


    /**
     * Set entity fields with the vales specified in an XML node representation
     * @param element an node in the XML representation
     */
    @Override
    public void getEntityFromNode(Element element){
        this.setId(Long.valueOf(element.getAttribute("id")));
        this.model = element.getElementsByTagName("model").item(0).getTextContent();
        this.manufacturer = element.getElementsByTagName("manufacturer").item(0).getTextContent();
        this.type = element.getElementsByTagName("type").item(0).getTextContent();
        this.weight = Float.parseFloat(element.getElementsByTagName("weight").item(0).getTextContent());
        this.price = Float.parseFloat(element.getElementsByTagName("price").item(0).getTextContent());
    }

    /**
     * Add an XML node to the document with the attributes of the specific clients
     * @param document an XML document
     * @return return an element in the XML document representing a gun
     */
    @Override
    public Node getNodeFromEntity(Document document) {
        Element entityElem = document.createElement("gun");
        entityElem.setAttribute("id", getId().toString());
        BaseEntity.addChildWithTextContent(document, entityElem, "model", String.valueOf(model));
        BaseEntity.addChildWithTextContent(document, entityElem, "manufacturer", String.valueOf(manufacturer));
        BaseEntity.addChildWithTextContent(document, entityElem, "type", String.valueOf(type));
        BaseEntity.addChildWithTextContent(document, entityElem, "weight", String.valueOf(weight));
        BaseEntity.addChildWithTextContent(document, entityElem, "price", String.valueOf(price));
        return entityElem;
    }

    /**
     * Get the sql specific queries for the specified entity (CRUD operations)
     * @return map of CRUD operations and Client specific queries for the client
     */
    @Override
    public Map<String,String> getSqlStmts(){
        return Stream.of(new String[][] {
                { "selectOne", "select * from guns where id = ?" },
                { "selectAll", "select * from guns" },
                { "insert", "insert into guns  (id, model, manufacturer, type, weight, price) values (?, ?, ?, ?, ?, ?)" },
                { "update", "update guns set model=?, manufacturer=?, type=?, weight=?, price=? where id=?" },
                { "delete", "delete from guns where id = ?" },
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
        this.model = rs.getString("model");
        this.manufacturer = rs.getString("manufacturer");
        this.type = rs.getString("type");
        this.weight =  rs.getFloat("weight");
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
                ps.setString(2, model);
                ps.setString(3, manufacturer);
                ps.setString(4, type);
                ps.setFloat(5, weight);
                ps.setFloat(6, price);
            }
            case "update" -> {
                ps.setString(1, model);
                ps.setString(2, manufacturer);
                ps.setString(3, type);
                ps.setFloat(4, weight);
                ps.setFloat(5, price);
                ps.setLong(6, getId());
            }
        }
    }


}
