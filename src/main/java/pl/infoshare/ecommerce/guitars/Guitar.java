package pl.infoshare.ecommerce.guitars;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.math.BigDecimal;

@Data
@Builder
public class Guitar {

    private final String id;
    private final String name;
    private final BigDecimal price;
    private Integer stock;
}
