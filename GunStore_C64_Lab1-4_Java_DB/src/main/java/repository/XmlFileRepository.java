package repository;

import domain.BaseEntity;
import domain.validators.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Optional;

public class XmlFileRepository <ID, T extends BaseEntity<ID>> extends InMemoryRepository<ID,T>{
    private final String fileName;

    /**
     * Creates a new XML file T repository.
     * @param validator The validator for a T
     * @param clazz The class of T
     * @param fileName The XML file path
     */
    public XmlFileRepository(Validator<T> validator, Class<T> clazz, String fileName) {
        super(validator);
        this.fileName = fileName;
        loadXMLData(clazz);
    }


    /**
     * Load XML data.
     * @param clazz the class of items that are represented in the XML file (Java Generics stuff here)
     */
    private void loadXMLData(Class<T> clazz) {
        try {

            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document root = db.parse(fileName);

            Element gunStore = root.getDocumentElement();
            NodeList items = gunStore.getChildNodes();
            for (int i = 0; i < items.getLength(); ++i) {
                if (items.item(i) instanceof Element) {
                    T entity = clazz.getDeclaredConstructor().newInstance();
                    entity.getEntityFromNode((Element) items.item(i));
                    super.save(entity);
                }
            }
        } catch (Exception e) {
            try { writeData(); }
            catch (Exception ex)
            { ex.printStackTrace(); }
        }
    }

    /**
     * Saves an entity to the repository
     * @param entity T to be saved
     * @return If added successfully, the entity is returned
     * @throws Exception If the entity cannot be added to the repo.
     */
    @Override
    public Optional<T> save(T entity) throws Exception {
        Optional<T> opt = super.save(entity);
        if(opt.isPresent())
            return opt;
        writeData();
        return Optional.empty();
    }

    /**
     * Delete an entity with a given ID
     * @param  id the given ID
     * @return the deleted entity
     */
    @Override
    public Optional<T> delete(ID id) throws Exception{
        Optional<T> opt = super.delete(id);
        if (opt.isEmpty())
            return Optional.empty();
        writeData();
        return opt;
    }

    /**
     * update a specific entity
     * @param entity the entity to be updated (id must match one existant in the repo
     * @return an optional, the entity if it was successfully updated
     * @throws Exception is raised by the base class if the ID does not exist in the repo
     */
    @Override
    public Optional<T> update(T entity) throws Exception {
        Optional<T> opt = super.update(entity);
        if (opt.isEmpty())
            return Optional.empty();
        writeData();
        return opt;
    }

    /**
     * Write the entities to their XML file
     * @throws Exception is raised if the elements cannot be written to the file (IO Error)
     */
    private void writeData() throws Exception{
        Document document = DocumentBuilderFactory
                .newInstance()
                .newDocumentBuilder()
                .newDocument();

        Element root = document.createElement("items");
        document.appendChild(root);
        super.findAll().forEach(entity -> {
            Node child = entity.getNodeFromEntity(document);
            root.appendChild(child);
        });

        Transformer transformer = TransformerFactory.
                newInstance().
                newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File(fileName));
        transformer.transform(domSource, streamResult);
    }

}
