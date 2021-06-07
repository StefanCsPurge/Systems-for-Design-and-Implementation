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
import ro.ubb.gunstore.web.dto.StoreOrderDto;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StoreOrderController {
    private static final Logger log = LoggerFactory.getLogger(StoreOrderController.class);

    @Autowired
    private OrderServiceInterface orderService;

    @Autowired
    private StoreOrderConverter storeOrderConverter;

    @RequestMapping(value = "/storeOrders/{id}", method=RequestMethod.GET)
    List<StoreOrderDto> getAllClientOrders(@PathVariable Long id){
        log.info("getAllClientOrders - method entered");
        var result =
                storeOrderConverter.convertModelsToDtos(orderService.getAllClientOrders(id));
        log.info("getAllClientOrders: result={}",result);
        return result;
    }

    @RequestMapping(value = "/storeOrders", method = RequestMethod.POST)
     StoreOrderDto addStoreOrder (@RequestBody StoreOrderDto storeOrderDto){
        log.info("addStoreOrder - method entered: storeOrderDto={}", storeOrderDto);
        StoreOrder storeOrder = storeOrderConverter.convertDtoToModel(storeOrderDto);
        StoreOrderDto result;
        try {
            var addedOrder = orderService.addOrder(storeOrder);
            result = storeOrderConverter.convertModelToDto(addedOrder);
        } catch (Exception e) {
            e.printStackTrace();
            result = new StoreOrderDto();
            result.setId(-1L);
        }
        log.info("addStoreOrder: result={}",result);
        return result;
    }

    @RequestMapping(value = "/storeOrders/{id}", method = RequestMethod.PUT)
    StoreOrderDto updateClientOrder(@RequestBody StoreOrderDto dto){
        log.info("updateClientOrder - method entered: orderDto={}", dto);
        StoreOrderDto result;
        try {
            result = storeOrderConverter.convertModelToDto(
                    orderService.updateClientOrder(storeOrderConverter.convertDtoToModel(dto)));
        } catch (Exception e) {
            result = new StoreOrderDto();
            result.setId(-1L);
        }
        log.info("updateClientOrder: result={}",result);
        return result;
    }

    @RequestMapping(value = "/storeOrders/{id}-{id2}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteClientOrder(@PathVariable Long id, @PathVariable Long id2) {
        log.info("deleteStoreOrder - method entered: clientId={} storeOrderId={}", id, id2);
        var result = new ResponseEntity<>(HttpStatus.OK);
        try {
            orderService.deleteClientOrder(id,id2);
        } catch (Exception e) {
            System.out.println("Delete error");
            System.out.println(e.getMessage());
            result = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("deleteStoreOrder: result={}",result);
        return result;
    }

    @RequestMapping(value = "/storeOrders/deleteOrdersGunId/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteAllByGunId(@PathVariable Long id) {
        log.info("deleteAllByGunId - method entered: gunId={}", id);
        var result = new ResponseEntity<>(HttpStatus.OK);
        try {
            orderService.deleteAllByGunId(id);
        } catch (Exception e) {
            result = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("deleteAllByGunId: result={}",result);
        return result;
    }

}
