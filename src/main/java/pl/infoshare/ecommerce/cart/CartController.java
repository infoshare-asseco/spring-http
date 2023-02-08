package pl.infoshare.ecommerce.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
@Validated
public class CartController {
    private static final String CART_ID_HEADER = "X-Cart-Id";

    private final CartService cartService;

    @GetMapping
    public Cart geyById(@RequestHeader(CART_ID_HEADER) String cartId, Principal principal) {
        Cart cartById = cartService.getCartById(cartId);
        if (cartById.getOwner().equals(principal.getName())) {
            return cartById;
        }
        throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cart createCart(Principal principal) {
        return cartService.createCart(principal);
    }

    @PutMapping("/guitars")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGuitarAmount(@RequestHeader(CART_ID_HEADER) String cartId, @Valid @RequestBody GuitarRequest guitarRequest) {
        cartService.setGuitarAmount(cartId, guitarRequest.getGuitarId(), guitarRequest.getAmount());
    }

    @DeleteMapping("/guitars/{guitarId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeGuitarFromCart(@RequestHeader(CART_ID_HEADER) String cartId, @PathVariable String guitarId) {
        cartService.removeGuitar(cartId, guitarId);
    }

    @PostMapping("/checkout")
    public void checkout(@RequestHeader(CART_ID_HEADER) String cartId) {
        cartService.checkout(cartId);
    }
}
