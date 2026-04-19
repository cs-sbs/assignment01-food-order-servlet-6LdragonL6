package cs.sbs.web.model;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private final int id;
    private final String customer;
    private final String food;
    private final int quantity;

    private static final List<Order> ORDERS = new ArrayList<>();
    private static int nextId = 1001;

    public Order(int id, String customer, String food, int quantity) {
        this.id = id;
        this.customer = customer;
        this.food = food;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getCustomer() {
        return customer;
    }

    public String getFood() {
        return food;
    }

    public int getQuantity() {
        return quantity;
    }

    public static synchronized Order create(String customer, String food, int quantity) {
        Order order = new Order(nextId++, customer, food, quantity);
        ORDERS.add(order);
        return order;
    }

    public static synchronized Order findById(int id) {
        for (Order order : ORDERS) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

}
