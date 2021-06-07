package repository;

import domain.BaseEntity;
import domain.validators.Validator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.DriverManager;
import java.util.*;

public class JDBCRepository<ID, T extends BaseEntity<ID>> implements Repository<ID, T>{
    private final String dbCredentialsFile;
    private String url;
    private String user;
    private String password;
    protected Validator<T> validator;
    private final Class<T> tClass;
    private Map<String, String> sqlStatements;

    public JDBCRepository(Validator<T> validator, Class<T> clazz, String dbCredentialsFile) {
        this.validator = validator;
        this.dbCredentialsFile = dbCredentialsFile;
        this.tClass = clazz;
        loadCredentials();
        getEntitySqlStatements();
    }


    /**
     * Get the entity sql statements
     */
    private void getEntitySqlStatements(){
        try {
            this.sqlStatements = tClass.getDeclaredConstructor().newInstance().getSqlStmts();
        }
        catch(Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * function to load DB credentials from the connection string
     */
    public void loadCredentials(){
        Path path = Paths.get(dbCredentialsFile);
        List<String> credentials = new ArrayList<>();
        try {
            Files.lines(path).forEach(credentials::add);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.url = credentials.get(0).replaceAll("\\s+","");
        this.user = credentials.get(1).replaceAll("\\s+","");
        this.password = credentials.get(2).replaceAll("\\s+","");
    }

    /**
     * find a specific element from the repo given its id
     * @param id
     *            must be not null.
     * @return
     */
    @Override
    public Optional<T> findOne(ID id) {
        if (id == null)
            throw new IllegalArgumentException("id must not be null");

        String stmt = sqlStatements.get("selectOne");

        try (var connection = DriverManager.getConnection(url, user, password);
             var ps = connection.prepareStatement(stmt)) {

            T entity = tClass.getDeclaredConstructor().newInstance();
            entity.setId(id);
            entity.setSqlPrepStmtParameters(ps,"selectOne");
            var rs = ps.executeQuery();
            rs.next();
            entity.readEntityFromResultSet(rs);
            return Optional.of(entity);

        } catch (Exception ex) {
            // ex.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * Get all the entities from the repo
     * @return hashset of all the entities in the repo
     */
    @Override
    public HashSet<T> findAll() {
        HashSet<T> set = new HashSet<>();
        String stmt = sqlStatements.get("selectAll");

        try (var connection = DriverManager.getConnection(url, user, password);
             var ps = connection.prepareStatement(stmt);
             var rs = ps.executeQuery()) {
            while (rs.next()) {
                T entity = tClass.getDeclaredConstructor().newInstance();
                entity.readEntityFromResultSet(rs);
                set.add(entity);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return set;
    }

    /**
     * Saves an entity to the repository
     * @param entity T to be saved
     * @return If added successfully, the entity is returned
     * @throws Exception If the entity cannot be added to the repo.
     */
    @Override
    public Optional<T> save(T entity) throws Exception {
        validator.validate(entity);
        String stmt = sqlStatements.get("insert");
        try (var connection = DriverManager.getConnection(url, user, password);
             var ps = connection.prepareStatement(stmt)) {
            entity.setSqlPrepStmtParameters(ps,"insert");
            ps.executeUpdate();
        }
        return Optional.of(entity);
    }


    /**
     * Delete an entity with a given ID
     * @param  id the given ID
     * @return the deleted entity
     */
    @Override
    public Optional<T> delete(ID id) throws Exception {
        if (id == null)
            throw new IllegalArgumentException("id must not be null");
        Optional<T> opt = findOne(id);
        if(opt.isEmpty())
            return Optional.empty();
        String stmt = sqlStatements.get("delete");

        try (var connection = DriverManager.getConnection(url, user, password);
             var ps = connection.prepareStatement(stmt)) {
            opt.get().setSqlPrepStmtParameters(ps,"delete");
            ps.executeUpdate();
        }
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
        validator.validate(entity);

        String stmt = sqlStatements.get("update");

        try (var connection = DriverManager.getConnection(url, user, password);
             var ps = connection.prepareStatement(stmt)) {
            entity.setSqlPrepStmtParameters(ps,"update");
            ps.executeUpdate();
        }
        return Optional.of(entity);
    }
}
