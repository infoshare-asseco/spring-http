package pl.infoshare.ecommerce.cart;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CartRepository {
    private final Map<String, Cart> carts = new ConcurrentHashMap<>();

    public Optional<Cart> getById(String id) {
        return Optional.ofNullable(carts.get(id));
    }

    public void createCart(Cart cart) {
        carts.put(cart.getId(), cart);
    }

    public void remove(Cart cart) {
        carts.remove(cart.getId());
    }
}
