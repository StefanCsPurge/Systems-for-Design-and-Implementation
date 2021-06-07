package ro.ubb.gunstore.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.gunstore.core.model.Address;
import ro.ubb.gunstore.core.model.Client;
import ro.ubb.gunstore.web.dto.ClientDto;

@Component
public class ClientConverter extends BaseConverter<Client, ClientDto> {
    @Override
    public Client convertDtoToModel(ClientDto dto) {
        var model = new Client();
        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setCnp(dto.getCnp());
        model.setBudget(dto.getBudget());

        model.setAddress(new Address(dto.getStreetNumber(),
                                     dto.getStreet(),
                                     dto.getCity()));
        return model;
    }

    @Override
    public ClientDto convertModelToDto(Client client) {
        ClientDto dto = new ClientDto();
        dto.setId(client.getId());
        dto.setName(client.getName());
        dto.setCnp(client.getCnp());
        dto.setBudget(client.getBudget());
        dto.setStreetNumber(client.getAddress().getStreetNumber());
        dto.setStreet(client.getAddress().getStreet());
        dto.setCity(client.getAddress().getCity());
        return dto;
    }
}
