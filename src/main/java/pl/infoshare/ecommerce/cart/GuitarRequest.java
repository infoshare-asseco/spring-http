package pl.infoshare.ecommerce.cart;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class GuitarRequest {
    private String guitarId;
    @Min(value = 1, message = "Guitar amount must be greater than 0")
    @Max(value = 5, message = "You can reserve at most 5 guitars")
    private Integer amount;
}
