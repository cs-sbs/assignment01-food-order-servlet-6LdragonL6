package cs.sbs.web.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MenuItem {

    private final String name;
    private final int price;

    private static final List<MenuItem> MENU_ITEMS;

    static {
        List<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem("Fried Rice", 8));
        items.add(new MenuItem("Fried Noodles", 9));
        items.add(new MenuItem("Burger", 10));
        MENU_ITEMS = Collections.unmodifiableList(items);
    }

    public MenuItem(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public static List<MenuItem> listAll() {
        return MENU_ITEMS;
    }

    public static List<MenuItem> searchByName(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return MENU_ITEMS;
        }
        String lowerKeyword = keyword.toLowerCase();
        List<MenuItem> result = new ArrayList<>();
        for (MenuItem item : MENU_ITEMS) {
            if (item.getName().toLowerCase().contains(lowerKeyword)) {
                result.add(item);
            }
        }
        return result;
    }
}
