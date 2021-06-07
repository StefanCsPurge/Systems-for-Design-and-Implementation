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

import java.util.List;


@RestController
@RequestMapping("/api")
public class GunController {
    private static final Logger log = LoggerFactory.getLogger(GunController.class);

    @Autowired
    private GunServiceInterface gunService;

    @Autowired
    private GunConverter gunConverter;


    @RequestMapping(value = "/guns")
    List<GunDto> getAllGuns(){
        log.info("getAllGuns - method entered");
        var result = gunConverter.convertModelsToDtos(gunService.getAllGuns());
        log.info("getAllGuns: result={}",result);
        return result;
    }

    @RequestMapping(value = "/guns/filterModel/{model}", method = RequestMethod.GET)
    List<GunDto> filterGunsByModel(@PathVariable String model){
        log.info("filterGunsByModel - method entered");
        var result = gunConverter.convertModelsToDtos(gunService.filterGunsByModel(model));
        log.info("filterGunsByModel: result={}",result);
        return result;
    }

    @RequestMapping(value = "/guns/filterType/{typeStr}", method = RequestMethod.GET)
    List<GunDto> filterGunsByType(@PathVariable String typeStr){
        log.info("filterGunsByType - method entered");
        var result = gunConverter.convertModelsToDtos(gunService.filterGunsByType(typeStr));
        log.info("filterGunsByType: result={}",result);
        return result;
    }

    @RequestMapping(value = "/guns/sortPrice", method = RequestMethod.GET)
    List<GunDto> sortGunsByPrice(){
        log.info("sortGunsByPrice - method entered");
        var result = gunConverter.convertModelsToDtos(gunService.getSortedGuns());
        log.info("sortGunsByPrice: result={}",result);
        return result;
    }

    @RequestMapping(value = "/guns/top3sold", method = RequestMethod.GET)
    List<GunDto> top3SoldGuns(){
        log.info("top3SoldGuns - method entered");
        var result = gunConverter.convertModelsToDtos(gunService.getTop3SoldGuns());
        log.info("top3SoldGuns: result={}",result);
        return result;
    }

    @RequestMapping(value = "/guns", method = RequestMethod.POST)
    GunDto addGun(@RequestBody GunDto gunDto){
        log.info("addGun - method entered: gunDto={}", gunDto);
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
        log.info("addGun: result={}",result);
        return result;
    }

    @RequestMapping(value = "/guns/{id}", method = RequestMethod.PUT)
    GunDto updateGun(@PathVariable Long id, @RequestBody GunDto dto){
        log.info("updateGun - method entered: id={} gunDto={}",id,dto);
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
        log.info("updateGun: result={}",result);
        return result;
    }

    @RequestMapping(value = "/guns/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteGun(@PathVariable Long id) {
        log.info("deleteGun - method entered: gunID={}", id);
        var result = new ResponseEntity<>(HttpStatus.OK);
        try {
            var deleted = gunService.deleteGun(id);
            log.debug("deleted gun: {}", deleted);
        } catch (Exception e) {
            // e.printStackTrace();
            result = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("deleteGun: result={}",result);
        return result;
    }
}