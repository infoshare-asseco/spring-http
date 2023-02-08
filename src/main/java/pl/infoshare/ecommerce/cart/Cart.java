package pl.infoshare.ecommerce.cart;

import lombok.Builder;
import lombok.Value;

import java.util.Map;

@Value
@Builder
public class Cart {

    private final String id;
    private final String owner;
    private final Map<String, Integer> guitarToAmount;

}
