package domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Generic class for a domain entity with an <code>id</code> attribute.
 * @param <ID> The identification attribute (key) of a domain entity.
 */

@MappedSuperclass
public class BaseEntity<ID extends Serializable> implements Serializable {
    @Id
    @GeneratedValue
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
}