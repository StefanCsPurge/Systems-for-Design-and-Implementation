package ro.ubb.gunstore.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccessoriesDto {
    private List<AccessoryDto> accessories;

    @Override
    public String toString() {
        return "AccessoriesDto{" +
                accessories.stream().map(e -> "\n\t" + e.toString()).reduce("", String::concat)
                + " }";
    }
}
