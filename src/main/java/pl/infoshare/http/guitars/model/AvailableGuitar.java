package pl.infoshare.http.guitars.model;

public class AvailableGuitar {
    private final String id;
    private final String name;
    private final Integer price;
    private final Integer stock;

    public AvailableGuitar(String id, String name, Integer price, Integer stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }
}
