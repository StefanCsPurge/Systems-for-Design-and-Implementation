package ro.ubb.gunstore.core.repository;

import ro.ubb.gunstore.core.model.Ammunition;
import ro.ubb.gunstore.core.repository.ammoCustomRepo.AmmoRepositoryCustom;

public interface AmmoRepository extends Repository<Long, Ammunition>, AmmoRepositoryCustom {
}
