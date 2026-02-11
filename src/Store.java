import java.util.ArrayList;
import java.util.HashMap;
import Cart.*;

public class Store {

    private final HashMap<String, ArrayList<Item>> categories;

    public Store() {
        categories = new HashMap<>();
        loadDrinks();
        loadSnacks();
    }

    private void loadDrinks() {
        ArrayList<Item> drinks = new ArrayList<>();

        drinks.add(new Item("A: (L)Dr.Pepper", 6.50));
        drinks.add(new Item("B: (M)Sprite", 3.00));
        drinks.add(new Item("C: (L)Sprite", 6.50));
        drinks.add(new Item("D: (L)Water", 4.50));

        categories.put("drinks", drinks);
    }

    private void loadSnacks() {
        ArrayList<Item> snacks = new ArrayList<>();

        snacks.add(new Item("A: Chips", 2.00));
        snacks.add(new Item("B: Candy", 1.50));
        snacks.add(new Item("C: Cookies", 3.00));

        categories.put("snacks", snacks);
    }


    public boolean removeItem(String category, String itemName) {
        ArrayList<Item> items = categories.get(category.toLowerCase());
        if (items != null) {
            return items.removeIf(item -> item.getName().equalsIgnoreCase(itemName));
        }
        return false;
    }


    public void updateItemPrice(String category, String itemName, double newPrice) {
        ArrayList<Item> items = categories.get(category.toLowerCase());
        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getName().equalsIgnoreCase(itemName)) {
                    items.set(i, new Item(itemName, newPrice));
                    return;
                }
            }
        }
    }


    public ArrayList<Item> getCategory(String categoryName) {
        return categories.get(categoryName.toLowerCase());
    }
}



