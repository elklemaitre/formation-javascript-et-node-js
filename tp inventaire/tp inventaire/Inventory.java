package fr.tpjava;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private static List<Item> items = new ArrayList<>();

    public static void addItem(String name, int quantity) {
        for (Item item : items) {
            if (item.getName().equals(name)) {
                item.setQuantity(quantity);
                return;
            }
        }
        items.add(new Item(name, quantity));
    }

    public static List<String> displayItems() {
        List<String> itemsList = new ArrayList<>();
        for (Item item : items) {
            itemsList.add(item.getName() + " : " + item.getQuantity());
        }
        return itemsList;
    }

    public static void removeItem(String name) {
        for (Item item : items) {
            if (item.getName().equals(name)) {
                items.remove(item);
                return;
            }
        }
    }
}
