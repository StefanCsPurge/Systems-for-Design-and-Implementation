package ro.ubb.gunstore.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.gunstore.core.model.BaseEntity;
import ro.ubb.gunstore.core.model.StoreOrder;
import ro.ubb.gunstore.core.model.exceptions.RepositoryException;
import ro.ubb.gunstore.core.model.validators.OrderValidator;
import ro.ubb.gunstore.core.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements OrderServiceInterface {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private GunServiceInterface gunService;
    @Autowired
    private ClientServiceInterface clientService;
    @Autowired
    private OrderValidator orderValidator;

    @Override
    public StoreOrder addOrder(StoreOrder storeOrder) throws Exception {
        log.trace("addOrder - method entered: order={}", storeOrder);
        orderValidator.validate(storeOrder);

        StoreOrder result;

        if(gunService.existsGun(storeOrder.getGunId()) && clientService.existsClient(storeOrder.getClientId()))
            result = orderRepository.save(storeOrder);
        else
            throw new Exception("Non existent gunId/clientId!");

        log.trace("addOrder - method finished");
        return result;
    }

    @Override
    public StoreOrder delete(Long id) {
        log.trace("deleteOrder - method entered: orderID={}", id);
        Optional<StoreOrder> opt = orderRepository.findById(id);
        if(opt.isEmpty())
            throw new RepositoryException("Order with the id " + id + " is not in the repository.");
        orderRepository.deleteById(id);
        log.trace("deleteOrder: result={}",opt.get());
        return opt.get();
    }

    @Override
    public StoreOrder update(StoreOrder entity) throws Exception {
        log.trace("update - method entered: entity={}", entity);
        orderValidator.validate(entity);
        var result = orderRepository.save(entity);
        log.debug("update - updated: entity={}", entity);
        log.trace("update - result={}", result);
        return result;
    }

    @Override
    public void deleteAllByGunId(Long gunId) {
        log.trace("deleteAllByGunId - method entered");
        orderRepository.findAll().stream()
                .filter((order) -> order.getGunId().equals(gunId))
                .map((BaseEntity::getId))
                .forEach(id -> {
                    try {
                        orderRepository.deleteById(id);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        log.trace("deleteAllByGunId - method finished");
    }

    @Override
    public List<StoreOrder> getAllOrders() {
        log.trace("getAllOrders - method entered");
        var result = orderRepository.findAll();
        log.trace("getAllOrders: result={}",result);
        return result;
    }
}
