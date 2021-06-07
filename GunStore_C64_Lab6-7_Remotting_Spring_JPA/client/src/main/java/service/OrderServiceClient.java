package service;

import domain.StoreOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceClient implements OrderServiceInterface {
    @Qualifier("orderServiceServer")
    @Autowired
    private OrderServiceInterface orderService;

    @Override
    public void addOrder(StoreOrder storeOrder) throws Exception {
        orderService.addOrder(storeOrder);
    }

    @Override
    public StoreOrder delete(Long aLong) throws Exception {
        return orderService.delete(aLong);
    }

    @Override
    public void update(StoreOrder entity) throws Exception {
        orderService.update(entity);
    }

    @Override
    public List<StoreOrder> getAllOrders() {
        return orderService.getAllOrders();
    }

    @Override
    public void deleteAllByGunId(Long gunId) {}
}
