package Cart;
import java.util.ArrayList;

public class Cart {
    private final ArrayList<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }

    public double getTotal() {
        double total = 0;
        for (Item item : items) {
            total += item.getPrice();
        }
        return total;
    }


    public String getItemNames() {
        StringBuilder result = new StringBuilder();
        for (Item item : items) {
            result.append(item.getName()).append(" ");
        }
        return result.toString();
    }

}

