package pl.infoshare.http.guitars;

import org.springframework.stereotype.Component;
import pl.infoshare.http.guitars.client.AddGuitarRequest;
import pl.infoshare.http.guitars.client.GuitarShopClient;
import pl.infoshare.http.guitars.client.Guitar;

@Component
public class GuitarPurchaseService {

    private final GuitarShopClient guitarShopClient;

    public GuitarPurchaseService(GuitarShopClient guitarShopClient) {
        this.guitarShopClient = guitarShopClient;
    }

    public void purchase(String id) {
        var guitar = guitarShopClient.getGuitars()
                .stream()
                .filter(g -> g.getId().equals(id))
                .findFirst()
                .filter(Guitar::isAvailable)
                .orElseThrow(() -> new GuitarNotAvailableException(id));

        var cart = guitarShopClient.createCart();
        guitarShopClient.addGuitarToCart(cart.getId(), new AddGuitarRequest(guitar.getId(), 1));
        guitarShopClient.purchaseCart(cart.getId());
    }
}
