package Cart;

public record Item(String item, double price) {

    public Item(String item, double price) {
        this.item = item;
        this.price = price;
    }

    public String getName() {
        return item;
    }

    public double getPrice() {
        return price;
    }
}


