package ro.ubb.gunstore.client.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ro.ubb.gunstore.web.dto.StoreOrdersDto;

@Component
public class PrintStoreOrderCommand extends Command {
    @Autowired
    private RestTemplate restTemplate;

    public PrintStoreOrderCommand() {super("printStoreOrders", "prints the store's orders");};

    public PrintStoreOrderCommand(String key, String desc) {
        super(key, desc);
    }

    @Override
    public void execute() throws Exception {
        StoreOrdersDto storeOrdersDto = restTemplate.getForObject("http://localhost:8080/api/storeOrders", StoreOrdersDto.class);
        System.out.println(storeOrdersDto);
    }

}
