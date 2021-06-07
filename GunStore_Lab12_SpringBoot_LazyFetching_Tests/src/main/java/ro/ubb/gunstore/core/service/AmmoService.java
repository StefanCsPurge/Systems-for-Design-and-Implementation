package ro.ubb.gunstore.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.gunstore.core.model.Ammunition;
import ro.ubb.gunstore.core.model.exceptions.RepositoryException;
import ro.ubb.gunstore.core.model.validators.AmmoValidator;
import ro.ubb.gunstore.core.repository.AmmoRepository;

import java.util.List;


@Service
public class AmmoService implements AmmoServiceInterface{
    private static final Logger log = LoggerFactory.getLogger(AmmoService.class);

    @Autowired
    private AmmoRepository repository;
    @Autowired
    private AmmoValidator ammoValidator;

    @Override
    public Ammunition addAmmunition(Ammunition entity) {
        log.trace("addAmmo - method entered: ammo={}", entity);
        ammoValidator.validate(entity);
        var result = repository.save(entity);
        log.trace("addAmmo - result={}", result);
        return result;
    }

    @Override
    public Ammunition delete(Long id) {
        log.trace("deleteAmmo - method entered: ammoID={}", id);
        var opt = repository.findById(id);
        if(opt.isEmpty())
            throw new RepositoryException("Ammunition with the id " + id + " is not in the repository.");
        repository.deleteById(id);
        log.trace("deleteAmmo: result={}",opt.get());
        return opt.get();
    }

    @Override
    public Ammunition update(Ammunition entity) {
        log.trace("updateAmmo - method entered: ammo={}", entity);
        ammoValidator.validate(entity);
        var result = repository.save(entity);
        log.debug("updateAmmo - updated: ammo={}", entity);
        log.trace("updateAmmo - result={}", result);
        return result;
    }

    @Override
    public Boolean existsAmmunition(Long id) {
        log.trace("existsAmmo - method entered: ammoID={}", id);
        var result = repository.findById(id).isPresent();
        log.trace("existsAmmo: result={}", result);
        return result;
    }

    @Override
    public List<Ammunition> getAllAmmunition() {
        log.trace("getAllAmmunition - method entered");
        var result =  repository.findAll();
        log.trace("getAllAmmunition: result={}",result);
        return result;
    }
}
