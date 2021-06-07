package ro.ubb.gunstore.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.gunstore.core.model.Gun;
import ro.ubb.gunstore.web.dto.GunDto;

import java.util.ArrayList;

@Component
public class GunConverter extends BaseConverter<Gun, GunDto>{
    @Override
    public Gun convertDtoToModel(GunDto dto) {
        var model = new Gun();
        model.setId(dto.getId());
        model.setModel(dto.getModel());
        model.setManufacturer(dto.getManufacturer());
        model.setType(dto.getType());
        model.setPrice(dto.getPrice());
        model.setWeight(dto.getWeight());
        model.setTimesSold(dto.getTimesSold());
        model.setGunAccessories(new ArrayList<>());
        return model;
    }

    @Override
    public GunDto convertModelToDto(Gun gun) {
        GunDto dto = GunDto.builder()   // builder pattern
                .model(gun.getModel())
                .manufacturer(gun.getManufacturer())
                .price(gun.getPrice())
                .type(gun.getType())
                .weight(gun.getWeight())
                .timesSold(gun.getTimesSold())
                .build();
        dto.setId(gun.getId());
        return dto;
    }
}
