package service;

import domain.BaseEntity;
import domain.StoreOrder;

import domain.exceptions.RepositoryException;
import domain.validators.OrderValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements OrderServiceInterface {
    private static final Logger log = LoggerFactory.getLogger("gun_store_server");

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private GunServiceInterface gunService;
    @Autowired
    private ClientServiceInterface clientService;

    @Autowired
    private OrderValidator orderValidator;

    @Override
    public void addOrder(StoreOrder storeOrder) throws Exception {
        log.trace("addOrder - method entered: order={}", storeOrder);
        orderValidator.validate(storeOrder);
        if(gunService.existsGun(storeOrder.getGunId()) && clientService.existsClient(storeOrder.getClientId()))
            orderRepository.save(storeOrder);
        else
            throw new Exception("Non existent gunId/clientId!");
        log.trace("addOrder - method finished");
    }

    @Override
    public StoreOrder delete(Long id) {
        log.trace("deleteOrder - method entered: orderID={}", id);
        Optional<StoreOrder> opt = orderRepository.findById(id);
        if(opt.isEmpty())
            throw new RepositoryException("Order with the id " + id.toString() + " is not in the repository.");
        orderRepository.deleteById(id);
        log.trace("deleteOrder: result={}",opt.get());
        return opt.get();
    }

    @Override
    public void update(StoreOrder entity) throws Exception {
        log.trace("updateOrder - method entered: order={}", entity);
        if(gunService.existsGun(entity.getGunId()) && clientService.existsClient(entity.getClientId()))
            orderRepository.save(entity);
        else
            throw new Exception("non existent gunId/clientId");
        log.debug("updateOrder - updated: order={}", entity);
        log.trace("updateOrder - method finished");
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
