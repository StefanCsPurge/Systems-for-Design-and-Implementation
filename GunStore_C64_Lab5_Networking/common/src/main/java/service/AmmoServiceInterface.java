package service;
import domain.Ammunition;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface AmmoServiceInterface {
    CompletableFuture<Optional<Ammunition>> addAmmunition(Ammunition entity) throws Exception;

    CompletableFuture<Optional<Ammunition>> delete(Long aLong) throws Exception;

    CompletableFuture<Optional<Ammunition>> update(Ammunition entity) throws Exception;

    CompletableFuture<Boolean> existsAmmunition(Long id);

    CompletableFuture<Set<Ammunition>> getAllAmmunition();

}
