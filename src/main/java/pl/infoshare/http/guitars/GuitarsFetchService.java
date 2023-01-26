package pl.infoshare.http.guitars;

import org.springframework.stereotype.Component;
import pl.infoshare.http.guitars.client.Guitar;
import pl.infoshare.http.guitars.client.GuitarShopClient;
import pl.infoshare.http.guitars.model.AvailableGuitar;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GuitarsFetchService {

    private final GuitarShopClient guitarShopClient;

    public GuitarsFetchService(GuitarShopClient guitarShopClient) {
        this.guitarShopClient = guitarShopClient;
    }

    public List<AvailableGuitar> fetchAllGuitars() {
        return guitarShopClient.getGuitars()
                .stream()
                .filter(Guitar::isAvailable)
                .map(AvailableGuitar::fromGuitar)
                .sorted(Comparator.comparing(AvailableGuitar::getStock).reversed())
                .collect(Collectors.toList());
    }
}
