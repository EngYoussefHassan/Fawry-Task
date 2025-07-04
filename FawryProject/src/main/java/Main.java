import java.util.*;

public class Main {
    public static void main(String[] args) {
        Product cheese = new ExpiringShippableProduct("Cheese", 100, 10, 0.4, "2025-08-01");
        Product biscuits = new ExpiringShippableProduct("Biscuits", 150, 5, 0.7, "2026-01-01");
        Product tv = new NonExpiringShippableProduct("TV", 150, 3, 10.0);
        Product scratchCard = new NonShippableNonExpiringProduct("Scratch Card", 50, 10);

        Customer customer = new Customer("Youssef", 1000.0);

        Cart cart = new Cart();
        cart.add(cheese, 2);
        cart.add(biscuits, 1);
        cart.add(tv, 1);
        cart.add(scratchCard, 1);

        CheckoutService.checkout(customer, cart);
    }
}