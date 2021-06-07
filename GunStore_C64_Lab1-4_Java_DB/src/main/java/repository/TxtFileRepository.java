package repository;

import domain.BaseEntity;
import domain.validators.Validator;

import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

public class TxtFileRepository<ID, T extends BaseEntity<ID>> extends InMemoryRepository<ID,T>{
    private final String fileName;

    /**
     * Creates a new file T repository.
     * @param validator The validator for a T
     */
    public TxtFileRepository(Validator<T> validator, Class<T> clazz,  String fileName) {
        super(validator);
        this.fileName = fileName;
        loadData(clazz);
    }

    /**
     * Refresh all data in the repository with that in the CSV file
     * @param clazz The class of entities that are in the CSV file
     */
    private void loadData(Class<T> clazz){
        Path path = Paths.get(fileName);
        try {
            Files.lines(path).forEach(line -> {
                try {
                    T entity = clazz.getDeclaredConstructor().newInstance();
                    entity.readEntityCSV(line);
                    super.save(entity);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
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
        saveToFile(entity);
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
        writeData();
        return opt;
    }


    /**
     * Save the whole repository to file (usually called when the repo is modified)
     * @param entity
     * @throws Exception
     */
    private void saveToFile(T entity) throws Exception {
        Path path = Paths.get(fileName);
        try(BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            bufferedWriter.write(entity.getCSVString());
            bufferedWriter.newLine();
        }
    }

    /**
     * Write the entities to their CSV file.
     * @throws Exception exception is raied if the
     */
    private void writeData() throws Exception{
        Path path = Paths.get(fileName);
        StringBuilder stringBuilder = new StringBuilder();
        entities.entrySet().stream().sequential()
                .forEach((x) -> {
                    T entity = x.getValue();
                    stringBuilder.append(entity.getCSVString()).append("\n");
                });

        try(BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            writer.write(stringBuilder.toString());
        }
    }
}
