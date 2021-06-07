package ro.ubb.gunstore.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StoreOrdersDto {
    private List<StoreOrderDto> storeOrders;

    @Override
    public String toString() {
        return "StoreOrdersDto{" +
                storeOrders.stream().map(e -> "\n\t" + e.toString()).reduce("", String::concat)
                + " }";
    }

}
