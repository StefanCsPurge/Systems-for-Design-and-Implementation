package ro.ubb.gunstore.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.gunstore.core.model.Ammunition;
import ro.ubb.gunstore.web.dto.AmmoDto;

@Component
public class AmmoConverter extends BaseConverter<Ammunition, AmmoDto>{
    @Override
    public Ammunition convertDtoToModel(AmmoDto dto) {
        var model = new Ammunition();
        model.setId(dto.getId());
        model.setNoOfRounds(dto.getNoOfRounds());
        model.setCaliber(dto.getCaliber());
        model.setManufacturer(dto.getManufacturer());
        model.setPrice(dto.getPrice());

        return model;
    }

    @Override
    public AmmoDto convertModelToDto(Ammunition ammo) {
        AmmoDto dto = new AmmoDto();
        dto.setId(ammo.getId());
        dto.setNoOfRounds(ammo.getNoOfRounds());
        dto.setCaliber(ammo.getCaliber());
        dto.setManufacturer(ammo.getManufacturer());
        dto.setPrice(ammo.getPrice());

        return dto;
    }
}
