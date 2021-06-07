package ro.ubb.gunstore.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientsDto {
    private List<ClientDto> clients;

    @Override
    public String toString() {
        return "ClientsDto{" +
                clients.stream().map(e -> "\n\t" + e.toString()).reduce("", String::concat)
                + " }";
    }
}
