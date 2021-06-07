package ro.ubb.gunstore.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AmmosDto {
    private List<AmmoDto> ammo;

    @Override
    public String toString() {
        return "AmmosDto{" +
                ammo.stream().map(e -> "\n\t" + e.toString()).reduce("", String::concat)
                + " }";
    }
}
