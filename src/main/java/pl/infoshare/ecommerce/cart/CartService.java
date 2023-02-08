package pl.infoshare.ecommerce.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.infoshare.ecommerce.guitars.Guitar;
import pl.infoshare.ecommerce.guitars.GuitarNotFoundException;
import pl.infoshare.ecommerce.guitars.GuitarRepository;

import java.security.Principal;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final GuitarRepository guitarRepository;

    public Cart getCartById(String id) {
        return cartRepository.getById(id).orElseThrow(CartNotFoundException::new);
    }

    public Cart createCart(Principal principal) {
        Cart cart = Cart.builder()
                .id(UUID.randomUUID().toString())
                .guitarToAmount(new ConcurrentHashMap<>())
                .owner(principal.getName())
                .build();
        cartRepository.createCart(cart);
        return cart;
    }

    public void setGuitarAmount(String cartId, String guitarId, Integer guitarAmount) {
        Cart cart = cartRepository.getById(cartId).orElseThrow(CartNotFoundException::new);
        Guitar guitar = guitarRepository.getById(guitarId).orElseThrow(GuitarNotFoundException::new);

        if (guitarAmount > guitar.getStock()) {
            throw new StockException();
        }

        cart.getGuitarToAmount().put(guitarId, guitarAmount);
    }

    public void removeGuitar(String cartId, String guitarId) {
        Cart cart = cartRepository.getById(cartId).orElseThrow(CartNotFoundException::new);
        cart.getGuitarToAmount().remove(guitarId);
    }

    public void checkout(String cartId) {
        Cart cart = cartRepository.getById(cartId).orElseThrow(CartNotFoundException::new);
        cart.getGuitarToAmount()
                .entrySet()
                .stream()
                .peek(g -> verifyStock(g.getKey(), g.getValue()))
                .forEach(g -> checkoutGuitar(g.getKey(), g.getValue()));
        cartRepository.remove(cart);
    }

    private void verifyStock(String guitarId, Integer amount) {
        Guitar guitar = guitarRepository.getById(guitarId).orElseThrow(GuitarNotFoundException::new);
        if (amount > guitar.getStock()) {
            throw new StockException();
        }
    }

    private void checkoutGuitar(String guitarId, Integer amount) {
        Guitar guitar = guitarRepository.getById(guitarId).orElseThrow(GuitarNotFoundException::new);
        guitar.setStock(guitar.getStock() - amount);
    }
}
