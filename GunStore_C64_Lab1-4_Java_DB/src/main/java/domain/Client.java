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
 * Object representing a client.
 */
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
    public Client(Long id, String name, String cnp, float budget) {
        this.setId(id);
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
    @Override
    public String getCSVString() {
        return getId() + "," + name + "," + cnp + "," + budget;
    }


    /**
     * Set entity fields with the values specified in a CSV line representation
     * @param line a CSV line representation of the client
     */
    @Override
    public void readEntityCSV(String line) {
        List<String> items = Arrays.asList(line.split(","));
        this.setId(Long.valueOf(items.get(0)));
        this.name = items.get(1);
        this.cnp = items.get(2);
        this.budget = Float.parseFloat(items.get(3));
    }


    /**
     * Set entity fields with the vales specified in an XML node representation
     * @param element an node in the XML representation
     */
    @Override
    public void getEntityFromNode(Element element){
        this.setId(Long.valueOf(element.getAttribute("id")));
        this.name = element.getElementsByTagName("name").item(0).getTextContent();
        this.cnp = element.getElementsByTagName("cnp").item(0).getTextContent();
        this.budget = Float.parseFloat(element.getElementsByTagName("budget").item(0).getTextContent());
    }


    /**
     * Add an XML node to the document with the attributes of the specific clients
     * @param document an XML document
     * @return return an element in the XML document representing a client
     */
    @Override
    public Node getNodeFromEntity(Document document) {
        Element entityElem = document.createElement("client");
        entityElem.setAttribute("id", getId().toString());
        BaseEntity.addChildWithTextContent(document, entityElem, "name", String.valueOf(name));
        BaseEntity.addChildWithTextContent(document, entityElem, "cnp", String.valueOf(cnp));
        BaseEntity.addChildWithTextContent(document, entityElem, "budget", String.valueOf(budget));
        return entityElem;
    }


    /**
     * Get the sql specific queries for the specified entity (CRUD operations)
     * @return map of CRUD operations and Client specific queries for the client
     */
    @Override
    public Map<String,String> getSqlStmts(){
        return Stream.of(new String[][] {
                { "selectOne", "select * from clients where id = ?" },
                { "selectAll", "select * from clients" },
                { "insert", "insert into clients  (id, name, cnp, budget) values (?, ?, ?, ?)" },
                { "update", "update clients set name=?, cnp=?, budget=? where id=?" },
                { "delete", "delete from clients where id = ?" },
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
        this.cnp = rs.getString("cnp");
        this.budget = rs.getFloat("budget");
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
                ps.setString(3, cnp);
                ps.setFloat(4, budget);
            }
            case "update" -> {
                ps.setString(1, name);
                ps.setString(2, cnp);
                ps.setFloat(3, budget);
                ps.setLong(4, getId());
            }
        }
    }
}
