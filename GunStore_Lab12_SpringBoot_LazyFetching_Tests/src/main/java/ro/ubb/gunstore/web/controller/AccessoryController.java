package ro.ubb.gunstore.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.gunstore.core.service.GunAccessoryServiceInterface;
import ro.ubb.gunstore.web.converter.AccessoryConverter;
import ro.ubb.gunstore.web.dto.AccessoryDto;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccessoryController {
    private static final Logger log = LoggerFactory.getLogger(AccessoryController.class);

    @Autowired
    private GunAccessoryServiceInterface accessoryService;

    @Autowired
    private AccessoryConverter accessoryConverter;


    @RequestMapping(value = "/accessories")
    List<AccessoryDto> getAllAccessories(){
        log.info("getAllAccessories - method entered");
        var result = accessoryConverter.convertModelsToDtos(accessoryService.getAllAccessories());
        log.info("getAllAccessories: result={}",result);
        return result;
    }

    @RequestMapping(value = "/accessories", method = RequestMethod.POST)
    AccessoryDto addAccessory(@RequestBody AccessoryDto accessoryDto){
        log.info("addAccessory - method entered: accessoryDto={}", accessoryDto);
        var accessory = accessoryConverter.convertDtoToModel(accessoryDto);

        AccessoryDto result = null;
        try {
            accessoryService.addAccessory(accessory);
        } catch (Exception e) {
            e.printStackTrace();
            result = new AccessoryDto();
            result.setName(e.getMessage());
        }
        log.info("addAccessory: result={}",result);
        return result;
    }

    @RequestMapping(value = "/accessories/{id}", method = RequestMethod.PUT)
    AccessoryDto updateAccessory(@PathVariable Long id, @RequestBody AccessoryDto dto){
        log.info("updateAccessory - method entered: id={} accessoryDto={}",id,dto);
        AccessoryDto result = null;
        try {
            accessoryService.updateAccessory(accessoryConverter.convertDtoToModel(dto));
        } catch (Exception e) {
            // e.printStackTrace();
            result = new AccessoryDto();
            result.setName(e.getMessage());
        }
        log.info("updateAccessory: result={}",result);
        return result;
    }

    @RequestMapping(value = "/accessories/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteAccessory(@PathVariable Long id) {
        log.info("deleteAccessory - method entered: accessoryID={}", id);
        var result = new ResponseEntity<>(HttpStatus.OK);
        try {
            accessoryService.deleteAccessory(id);
            log.debug("deleted accessory id: {}", id);
        } catch (Exception e) {
            // e.printStackTrace();
            result = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("deleteAccessory: result={}",result);
        return result;
    }
}
