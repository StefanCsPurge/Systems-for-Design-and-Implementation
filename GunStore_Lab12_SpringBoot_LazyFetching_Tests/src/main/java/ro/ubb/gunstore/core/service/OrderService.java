package ro.ubb.gunstore.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.gunstore.core.model.StoreOrder;
import ro.ubb.gunstore.core.model.validators.OrderValidator;
import ro.ubb.gunstore.core.repository.ClientRepository;
import ro.ubb.gunstore.core.repository.GunRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements OrderServiceInterface {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private GunRepository gunRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private OrderValidator orderValidator;

    @Override
    public StoreOrder addOrder(StoreOrder storeOrder) throws Exception {
        log.trace("addOrder - method entered: storeOrder={}", storeOrder);
        if (gunRepository.findGunWithOrders(storeOrder.getOrdered_gun().getId()) == null ||
            clientRepository.findClientWithOrders(storeOrder.getClient().getId()) == null)
            throw new Exception("There is no client/gun with this id!");

        orderValidator.validate(storeOrder);

        storeOrder.getClient().getClient_orders().add(storeOrder);
        //storeOrder.getGun().getOrders().add(storeOrder);

        clientRepository.save(storeOrder.getClient());
        //gunRepository.save(storeOrder.getGun());
        log.trace("addOrder - method finished");
        return storeOrder;
    }

    @Override
    public void deleteClientOrder(Long clientId, Long orderId) throws Exception {
        log.trace("deleteOrder - method entered: clientID={} orderID={}",clientId,orderId);
        var client = this.clientRepository.findClientWithOrders(clientId);
        if(client == null)
            throw new Exception("There is no client with the given ID");
        // var client = clientOptional.get();
        var storeOrderOptional = client.getClient_orders()
                .stream().filter(order -> order.getId().equals(orderId)).findFirst();
        if(storeOrderOptional.isEmpty())
            throw new Exception("There is no order with the given ID for this client");
        var storeOrder = storeOrderOptional.get();
        var gun = this.gunRepository.findGunWithOrders(storeOrder.getOrdered_gun().getId());

        client.getClient_orders().remove(storeOrder);
        gun.getGun_orders().remove(storeOrder);

        clientRepository.save(client);
        //gunRepository.save(gun);

        log.trace("deleteOrder: method finished");
    }

    @Override
    public StoreOrder updateClientOrder(StoreOrder updatedOrder) throws Exception {
        log.trace("update - method entered: entity={}", updatedOrder);
        orderValidator.validate(updatedOrder);
        deleteClientOrder(updatedOrder.getClient().getId(),updatedOrder.getId());
        addOrder(updatedOrder);
        log.trace("update - result={}", updatedOrder);
        return updatedOrder;
    }

    /**
     * Delete all orders containing a given gunId from the repository.
     * @param gunId the given gunId
     */
    @Override
    public void deleteAllByGunId(Long gunId) {
        log.trace("deleteAllByGunId - method entered");
        var gun = gunRepository.findGunWithOrders(gunId);
        if (gun == null) return;
        var gunOrders = new ArrayList<>(gun.getGun_orders());
        gunOrders.forEach(storeOrder ->
        {
            try {
                deleteClientOrder(storeOrder.getClient().getId(), storeOrder.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        log.trace("deleteAllByGunId - method finished");
    }

    @Override
    public List<StoreOrder> getAllClientOrders(Long clientId) {
        log.trace("getAllClientOrders - method entered");
        var result = new ArrayList<StoreOrder>();
        var client = clientRepository.findClientWithOrders(clientId);
        if(client != null)
            result = new ArrayList<>(client.getClient_orders());
        log.trace("getAllClientOrders: result={}",result);
        return result;
    }

    @Override
    public List<StoreOrder> getAllGunOrders(Long gunId){
        log.trace("getAllGunOrders - method entered");
        var result = new ArrayList<StoreOrder>();
        var gun = gunRepository.findGunWithOrders(gunId);
        if(gun != null)
            result = new ArrayList<>(gun.getGun_orders());
        log.trace("getAllGunOrders: result={}",result);
        return result;
    }
}
