package ro.ubb.gunstore.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.gunstore.core.model.Gun;
import ro.ubb.gunstore.web.dto.GunDto;

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
        return model;
    }

    @Override
    public GunDto convertModelToDto(Gun gun) {
        GunDto dto = new GunDto();
        dto.setId(gun.getId());
        dto.setModel(gun.getModel());
        dto.setManufacturer(gun.getManufacturer());
        dto.setPrice(gun.getPrice());
        dto.setType(gun.getType());
        dto.setWeight(gun.getWeight());
        dto.setTimesSold(gun.getTimesSold());

        return dto;
    }
}
