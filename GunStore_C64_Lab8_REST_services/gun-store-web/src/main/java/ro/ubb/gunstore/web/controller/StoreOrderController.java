package ro.ubb.gunstore.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.gunstore.core.model.StoreOrder;
import ro.ubb.gunstore.core.service.OrderServiceInterface;
import ro.ubb.gunstore.web.converter.StoreOrderConverter;
import ro.ubb.gunstore.web.dto.GunDto;
import ro.ubb.gunstore.web.dto.GunsDto;
import ro.ubb.gunstore.web.dto.StoreOrderDto;
import ro.ubb.gunstore.web.dto.StoreOrdersDto;

@RestController
public class StoreOrderController {
    private static final Logger log = LoggerFactory.getLogger(StoreOrderController.class);

    @Autowired
    private OrderServiceInterface orderService;

    @Autowired
    private StoreOrderConverter storeOrderConverter;

    @RequestMapping(value = "/storeOrders", method=RequestMethod.GET)
    StoreOrdersDto getAllStoreOrders(){
        log.trace("getAllStoreOrders - method entered");
        var result = new StoreOrdersDto(
                storeOrderConverter.convertModelsToDtos(orderService.getAllOrders())
        );
        log.trace("getAllStoreOrders: result={}",result);
        return result;
    }

    /*

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

    */


    @RequestMapping(value = "/storeOrders", method = RequestMethod.POST)
     StoreOrderDto addStoreOrder (@RequestBody StoreOrderDto storeOrderDto){
        log.trace("addStoreOrder - method entered: storeOrderDto={}", storeOrderDto);
        StoreOrder storeOrder = storeOrderConverter.convertDtoToModel(storeOrderDto);
        StoreOrderDto result;
        try {
            var resultStoreOrder = orderService.addOrder(storeOrder);
            result = storeOrderConverter.convertModelToDto(resultStoreOrder);
        } catch (Exception e) {
            // e.printStackTrace();
            result = new StoreOrderDto();
        }
        log.trace("addGun: result={}",result);
        return result;
    }

    @RequestMapping(value = "/storeOrders/{id}", method = RequestMethod.PUT)
    StoreOrderDto updateStoreOrder(@PathVariable Long id, @RequestBody StoreOrderDto dto){
        log.trace("updateStoreOrder - method entered: id={} gunDto={}",id,dto);
        StoreOrderDto result;
        try {
            result = storeOrderConverter.convertModelToDto(
                    orderService.update(storeOrderConverter.convertDtoToModel(dto)));
        } catch (Exception e) {
            result = new StoreOrderDto();
        }
        log.trace("updateGun: result={}",result);
        return result;
    }

    @RequestMapping(value = "/storeOrders/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteStoreOrder(@PathVariable Long id) {
        log.trace("deleteStoreOrder - method entered: storeOrderId={}", id);
        var result = new ResponseEntity<>(HttpStatus.OK);
        try {
            var deleted = orderService.delete(id);
            log.debug("deleted order: {}", deleted);
        } catch (Exception e) {
            result = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.trace("deleteStoreOrder: result={}",result);
        return result;
    }





    @RequestMapping(value = "/storeOrders/deleteOrdersGunId/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteAllByGunId(@PathVariable Long id) {
        log.trace("deleteAllByGunId - method entered: gunId={}", id);
        var result = new ResponseEntity<>(HttpStatus.OK);
        try {
            orderService.deleteAllByGunId(id);
        } catch (Exception e) {
            result = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.trace("deleteAllByGunId: result={}",result);
        return result;
    }



}
