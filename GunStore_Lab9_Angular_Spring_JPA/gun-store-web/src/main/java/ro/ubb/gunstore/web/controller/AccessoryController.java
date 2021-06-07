package ro.ubb.gunstore.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.gunstore.core.service.GunAccessoryServiceInterface;
import ro.ubb.gunstore.web.converter.AccessoryConverter;
import ro.ubb.gunstore.web.dto.AccessoriesDto;
import ro.ubb.gunstore.web.dto.AccessoryDto;

@RestController
public class AccessoryController {
    private static final Logger log = LoggerFactory.getLogger(AccessoryController.class);

    @Autowired
    private GunAccessoryServiceInterface accessoryService;

    @Autowired
    private AccessoryConverter accessoryConverter;


    @RequestMapping(value = "/accessories")
    AccessoriesDto getAllAccessories(){
        log.trace("getAllAccessories - method entered");
        var result = new AccessoriesDto(
                accessoryConverter.convertModelsToDtos(accessoryService.getAllAccessories())
        );
        log.trace("getAllAccessories: result={}",result);
        return result;
    }

    @RequestMapping(value = "/accessories", method = RequestMethod.POST)
    AccessoryDto addAccessory(@RequestBody AccessoryDto accessoryDto){
        log.trace("addAccessory - method entered: accessoryDto={}", accessoryDto);
        var accessory = accessoryConverter.convertDtoToModel(accessoryDto);
        AccessoryDto result;
        try {
            var resultAccessory = accessoryService.addAccessory(accessory);
            result = accessoryConverter.convertModelToDto(resultAccessory);
        } catch (Exception e) {
            // e.printStackTrace();
            result = new AccessoryDto();
            result.setName(e.getMessage());
        }
        log.trace("addAccessory: result={}",result);
        return result;
    }

    @RequestMapping(value = "/accessories/{id}", method = RequestMethod.PUT)
    AccessoryDto updateAccessory(@PathVariable Long id, @RequestBody AccessoryDto dto){
        log.trace("updateAccessory - method entered: id={} accessoryDto={}",id,dto);
        AccessoryDto result;
        try {
            result = accessoryConverter.convertModelToDto(
                    accessoryService.updateAccessory(accessoryConverter.convertDtoToModel(dto))
            );
        } catch (Exception e) {
            // e.printStackTrace();
            result = new AccessoryDto();
            result.setName(e.getMessage());
        }
        log.trace("updateAccessory: result={}",result);
        return result;
    }

    @RequestMapping(value = "/accessories/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteAccessory(@PathVariable Long id) {
        log.trace("deleteAccessory - method entered: accessoryID={}", id);
        var result = new ResponseEntity<>(HttpStatus.OK);
        try {
            var deleted = accessoryService.deleteAccessory(id);
            log.debug("deleted accessory: {}", deleted);
        } catch (Exception e) {
            // e.printStackTrace();
            result = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.trace("deleteAccessory: result={}",result);
        return result;
    }
}