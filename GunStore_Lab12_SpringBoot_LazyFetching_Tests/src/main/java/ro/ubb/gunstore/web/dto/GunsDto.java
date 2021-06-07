package ro.ubb.gunstore.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// RestTemplate object from the client requires a DTO to receive instead of List

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GunsDto {
    private List<GunDto> guns;

    @Override
    public String toString() {
        return "GunsDto{" +
                guns.stream().map(e -> "\n\t" + e.toString()).reduce("", String::concat)
                + " }";
    }
}
