package ro.ubb.gunstore.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.gunstore.core.service.AmmoServiceInterface;
import ro.ubb.gunstore.web.converter.AmmoConverter;
import ro.ubb.gunstore.web.dto.AmmoDto;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AmmoController {
    private static final Logger log = LoggerFactory.getLogger(AmmoController.class);

    @Autowired
    private AmmoServiceInterface ammoService;

    @Autowired
    private AmmoConverter ammoConverter;


    @RequestMapping(value = "/ammunition")
    List<AmmoDto> getAllAmmo(){
        log.trace("getAllAmmo - method entered");
        var result = ammoConverter.convertModelsToDtos(ammoService.getAllAmmunition());
        log.trace("getAllAmmo: result={}",result);
        return result;
    }

    @RequestMapping(value = "/ammunition", method = RequestMethod.POST)
    AmmoDto addAmmo(@RequestBody AmmoDto ammoDto){
        log.trace("addAmmo - method entered: ammoDto={}", ammoDto);
        var ammo = ammoConverter.convertDtoToModel(ammoDto);
        AmmoDto result;
        try {
            var resultAmmo = ammoService.addAmmunition(ammo);
            result = ammoConverter.convertModelToDto(resultAmmo);
        } catch (Exception e) {
            // e.printStackTrace();
            result = new AmmoDto();
            result.setManufacturer(e.getMessage());
        }
        log.trace("addAmmo: result={}",result);
        return result;
    }

    @RequestMapping(value = "/ammunition/{id}", method = RequestMethod.PUT)
    AmmoDto updateAmmo(@PathVariable Long id, @RequestBody AmmoDto dto){
        log.trace("updateAmmo - method entered: id={} ammoDto={}",id,dto);
        AmmoDto result;
        try {
            result = ammoConverter.convertModelToDto(
                    ammoService.update(ammoConverter.convertDtoToModel(dto))
            );
        } catch (Exception e) {
            // e.printStackTrace();
            result = new AmmoDto();
            result.setManufacturer(e.getMessage());
        }
        log.trace("updateAmmo: result={}",result);
        return result;
    }

    @RequestMapping(value = "/ammunition/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteAmmo(@PathVariable Long id) {
        log.trace("deleteAmmo - method entered: ammoID={}", id);
        var result = new ResponseEntity<>(HttpStatus.OK);
        try {
            var deleted = ammoService.delete(id);
            log.debug("deleted ammo: {}", deleted);
        } catch (Exception e) {
            // e.printStackTrace();
            result = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.trace("deleteAmmo: result={}",result);
        return result;
    }
}
