package domain;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Generic class for a domain entity with an <code>id</code> attribute.
 * @param <ID> The identification attribute (key) of a domain entity.
 */
public class BaseEntity<ID> {
    public BaseEntity(){}

    private ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BaseEntity{" + '\'' +
                "id=" + id + '\'' +
                '}'+ '\'';
    }

    public void readEntityCSV (String line) {}

    public void getEntityFromNode(Element entityElement) {}

    public Node getNodeFromEntity(Document document){
        Element entityElem = document.createElement("baseEntity");
        entityElem.setAttribute("id", id.toString());
        return entityElem;
    }

    public String getCSVString(){
        return id.toString();
    }

    protected static void addChildWithTextContent(Document document, Element parent, String tagName, String textContent) {
        Element childElement = document.createElement(tagName);
        childElement.setTextContent(textContent);
        parent.appendChild(childElement);
    }

    public Map<String,String> getSqlStmts() { return null; }

    public void setSqlPrepStmtParameters(PreparedStatement ps, String stmtName) throws SQLException {}

    public void readEntityFromResultSet(ResultSet rs) throws SQLException {}
}