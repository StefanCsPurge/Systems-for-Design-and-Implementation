package ro.ubb.gunstore.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.gunstore.core.model.GunAccessories;
import ro.ubb.gunstore.web.dto.AccessoryDto;

@Component
public class AccessoryConverter extends BaseConverter<GunAccessories, AccessoryDto>{
    @Override
    public GunAccessories convertDtoToModel(AccessoryDto dto) {
        var model = new GunAccessories();
        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setType(dto.getType());
        model.setCompany(dto.getCompany());
        model.setPrice(dto.getPrice());
        model.setGunId(dto.getGunId());
        return model;
    }

    @Override
    public AccessoryDto convertModelToDto(GunAccessories accessory) {
        AccessoryDto dto = new AccessoryDto();
        dto.setId(accessory.getId());
        dto.setName(accessory.getName());
        dto.setType(accessory.getType());
        dto.setCompany(accessory.getCompany());
        dto.setPrice(accessory.getPrice());
        dto.setGunId(accessory.getGunId());
        return dto;
    }
}
