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
 * Gun accessories object.
 */
public class GunAccessories extends BaseEntity<Long>{
    private String name;
    private String type;
    private String company;
    private float price;

    public GunAccessories() {}
    /**
     *
     * @param id  The id of the gun accessory
     * @param name What is the accessory
     * @param type The type of the accessory
     * @param company The company that produces that accessory
     * @param price The price of the accessory
     */
    public GunAccessories(Long id, String name, String type, String company, float price) {
        this.setId(id);
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
    @Override
    public String getCSVString() {
        return getId() + "," + name + "," + type + "," + company + "," + price;
    }


    /**
     * Set entity fields with the values specified in a CSV line representation
     * @param line a CSV line representation of the gun accessory
     */
    @Override
    public void readEntityCSV(String line) {
        List<String> items = Arrays.asList(line.split(","));
        this.setId(Long.valueOf(items.get(0)));
        this.name = items.get(1);
        this.type = items.get(2);
        this.company = items.get(3);
        this.price = Float.parseFloat(items.get(4));
    }

    /**
     * Set entity fields with the vales specified in an XML node representation
     * @param element an node in the XML representation
     */
    @Override
    public void getEntityFromNode(Element element){
        this.setId(Long.valueOf(element.getAttribute("id")));
        this.name = element.getElementsByTagName("name").item(0).getTextContent();
        this.type = element.getElementsByTagName("type").item(0).getTextContent();
        this.company = element.getElementsByTagName("company").item(0).getTextContent();
        this.price = Float.parseFloat(element.getElementsByTagName("price").item(0).getTextContent());
    }

    /**
     * Add an XML node to the document with the attributes of the specific gun accessories
     * @param document an XML document
     * @return return an element in the XML document representing a gun accessory
     */
    @Override
    public Node getNodeFromEntity(Document document) {
        Element entityElem = document.createElement("gun-accessory");
        entityElem.setAttribute("id", getId().toString());
        BaseEntity.addChildWithTextContent(document, entityElem, "name", String.valueOf(name));
        BaseEntity.addChildWithTextContent(document, entityElem, "type", String.valueOf(type));
        BaseEntity.addChildWithTextContent(document, entityElem, "company", String.valueOf(company));
        BaseEntity.addChildWithTextContent(document, entityElem, "price", String.valueOf(price));
        return entityElem;
    }

    /**
     * Get the sql specific queries for the specified entity (CRUD operations)
     * @return map of CRUD operations and Client specific queries for the gun accessory
     */
    @Override
    public Map<String,String> getSqlStmts(){
        return Stream.of(new String[][] {
                { "selectOne", "select * from gunaccessories where id = ?" },
                { "selectAll", "select * from gunaccessories" },
                { "insert", "insert into gunaccessories  (id, name, type, company, price) values (?, ?, ?, ?, ?)" },
                { "update", "update gunaccessories set name=?, type=?, company=?, price=? where id=?" },
                { "delete", "delete from gunaccessories where id = ?" },
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
        this.name = rs.getString("name");
        this.type =  rs.getString("type");
        this.company = rs.getString("company");
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
                ps.setString(2, name);
                ps.setString(3, type);
                ps.setString(4, company);
                ps.setFloat(5, price);
            }
            case "update" -> {
                ps.setString(1, name);
                ps.setString(2, type);
                ps.setString(3, company);
                ps.setFloat(4, price);
                ps.setLong(5, getId());
            }
        }
    }
}
