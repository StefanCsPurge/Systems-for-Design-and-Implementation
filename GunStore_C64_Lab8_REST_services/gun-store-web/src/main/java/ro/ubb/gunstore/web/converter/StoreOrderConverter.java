package ro.ubb.gunstore.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.gunstore.core.model.StoreOrder;
import ro.ubb.gunstore.web.dto.StoreOrderDto;
import ro.ubb.gunstore.web.dto.StoreOrdersDto;

@Component
public class StoreOrderConverter extends BaseConverter<StoreOrder, StoreOrderDto> {
    @Override
    public StoreOrder convertDtoToModel(StoreOrderDto dto) {
        var model = new StoreOrder();
        model.setId(dto.getId());
        model.setClientId(dto.getClientId());
        model.setGunId(dto.getGunId());
        return model;
    }

    @Override
    public StoreOrderDto convertModelToDto(StoreOrder storeOrder) {
        StoreOrderDto storeOrderDto = new StoreOrderDto();
        storeOrderDto.setId(storeOrder.getId());
        storeOrderDto.setClientId(storeOrder.getClientId());
        storeOrderDto.setGunId(storeOrder.getGunId());
        return storeOrderDto;
    }
}
