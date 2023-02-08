package pl.infoshare.ecommerce.guitars;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class GuitarRepository {

    private static final BigDecimal BASE_PRICE = new BigDecimal("50.00");
    private static final List<String> GUITAR_NAMES = List.of(
            "Fender",
            "Yamaha",
            "Ibanez",
            "Cort",
            "Squier",
            "Gibson",
            "Epiphone",
            "Dean"
    );

    private final Map<String, Guitar> store;
    private final Random random = new Random();

    public GuitarRepository() {
        store = GUITAR_NAMES.stream()
                .map(name -> createGuitar(UUID.randomUUID(), name))
                .collect(Collectors.toConcurrentMap(Guitar::getId, Function.identity()));
    }

    public Collection<Guitar> getAll() {
        return store.values();
    }

    public Optional<Guitar> getById(String id) {
        Guitar guitar = store.get(id);
        return Optional.ofNullable(guitar);
    }


    private Guitar createGuitar(UUID id, String name) {
        int randomMultiplier = randInt();
        int randomStock = randInt() < 50 ? 0 : randInt();
        return Guitar.builder()
                .id(id.toString())
                .price(BASE_PRICE.multiply(BigDecimal.valueOf(randomMultiplier)))
                .stock(randomStock * 10)
                .name(name)
                .build();
    }

    private int randInt() {
        return random.nextInt(100) + 20;
    }
}
