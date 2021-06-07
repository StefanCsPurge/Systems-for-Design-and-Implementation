package ro.ubb.gunstore.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.gunstore.core.service.GunServiceInterface;
import ro.ubb.gunstore.web.converter.GunConverter;
import ro.ubb.gunstore.web.dto.GunDto;
import ro.ubb.gunstore.web.dto.GunsDto;


@RestController
public class GunController {
    private static final Logger log = LoggerFactory.getLogger(GunController.class);

    @Autowired
    private GunServiceInterface gunService;

    @Autowired
    private GunConverter gunConverter;


    @RequestMapping(value = "/guns")
    GunsDto getAllGuns(){
        log.trace("getAllGuns - method entered");
        var result = new GunsDto(
                        gunConverter.convertModelsToDtos(gunService.getAllGuns())
                    );
        log.trace("getAllGuns: result={}",result);
        return result;
    }

    @RequestMapping(value = "/guns/filterModel/{model}", method = RequestMethod.GET)
    GunsDto filterGunsByModel(@PathVariable String model){
        log.trace("filterGunsByModel - method entered");
        var result = new GunsDto(
                gunConverter.convertModelsToDtos(gunService.filterGunsByModel(model))
        );
        log.trace("filterGunsByModel: result={}",result);
        return result;
    }

    @RequestMapping(value = "/guns/filterType/{typeStr}", method = RequestMethod.GET)
    GunsDto filterGunsByType(@PathVariable String typeStr){
        log.trace("filterGunsByType - method entered");
        var result = new GunsDto(
                gunConverter.convertModelsToDtos(gunService.filterGunsByType(typeStr))
        );
        log.trace("filterGunsByType: result={}",result);
        return result;
    }

    @RequestMapping(value = "/guns/sortPrice", method = RequestMethod.GET)
    GunsDto sortGunsByPrice(){
        log.trace("sortGunsByPrice - method entered");
        var result = new GunsDto(
                gunConverter.convertModelsToDtos(gunService.getSortedGuns())
        );
        log.trace("sortGunsByPrice: result={}",result);
        return result;
    }

    @RequestMapping(value = "/guns/top3sold", method = RequestMethod.GET)
    GunsDto top3SoldGuns(){
        log.trace("top3SoldGuns - method entered");
        var result = new GunsDto(
                gunConverter.convertModelsToDtos(gunService.getTop3SoldGuns())
        );
        log.trace("top3SoldGuns: result={}",result);
        return result;
    }

    @RequestMapping(value = "/guns", method = RequestMethod.POST)
    GunDto addGun(@RequestBody GunDto gunDto){
        log.trace("addGun - method entered: gunDto={}", gunDto);
        var gun = gunConverter.convertDtoToModel(gunDto);
        GunDto result;
        try {
            var resultGun = gunService.addGun(gun);
            result = gunConverter.convertModelToDto(resultGun);
        } catch (Exception e) {
            // e.printStackTrace();
            result = new GunDto();
            result.setModel(e.getMessage());
        }
        log.trace("addGun: result={}",result);
        return result;
    }

    @RequestMapping(value = "/guns/{id}", method = RequestMethod.PUT)
    GunDto updateGun(@PathVariable Long id, @RequestBody GunDto dto){
        log.trace("updateGun - method entered: id={} gunDto={}",id,dto);
        GunDto result;
        try {
            result = gunConverter.convertModelToDto(
                    gunService.updateGun(gunConverter.convertDtoToModel(dto))
            );
        } catch (Exception e) {
            // e.printStackTrace();
            result = new GunDto();
            result.setModel(e.getMessage());
        }
        log.trace("updateGun: result={}",result);
        return result;
    }

    @RequestMapping(value = "/guns/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteGun(@PathVariable Long id) {
        log.trace("deleteGun - method entered: gunID={}", id);
        var result = new ResponseEntity<>(HttpStatus.OK);
        try {
            var deleted = gunService.deleteGun(id);
            log.debug("deleted gun: {}", deleted);
        } catch (Exception e) {
            // e.printStackTrace();
            result = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.trace("deleteGun: result={}",result);
        return result;
    }
}
