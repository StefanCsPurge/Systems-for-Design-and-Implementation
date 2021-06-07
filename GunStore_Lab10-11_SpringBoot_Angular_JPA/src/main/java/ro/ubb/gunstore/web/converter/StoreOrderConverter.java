package ro.ubb.gunstore.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.ubb.gunstore.core.model.StoreOrder;
import ro.ubb.gunstore.core.repository.ClientRepository;
import ro.ubb.gunstore.core.repository.GunRepository;
import ro.ubb.gunstore.web.dto.StoreOrderDto;

@Component
public class StoreOrderConverter extends BaseConverter<StoreOrder, StoreOrderDto> {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private GunRepository gunRepository;

    @Override
    public StoreOrder convertDtoToModel(StoreOrderDto dto) {
        var model = new StoreOrder();
        model.setId(dto.getId());
        var clientOpt = clientRepository.findById(dto.getClientId());
        clientOpt.ifPresent(model::setClient);
        var gunOpt = gunRepository.findById(dto.getGunId());
        gunOpt.ifPresent(model::setOrdered_gun);
        return model;
    }

    @Override
    public StoreOrderDto convertModelToDto(StoreOrder storeOrder) {
        StoreOrderDto storeOrderDto = new StoreOrderDto();
        storeOrderDto.setId(storeOrder.getId());
        storeOrderDto.setClientId(storeOrder.getClient().getId());
        storeOrderDto.setGunId(storeOrder.getOrdered_gun().getId());
        return storeOrderDto;
    }
}
