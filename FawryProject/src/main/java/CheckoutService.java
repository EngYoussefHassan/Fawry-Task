import java.util.*;

public class CheckoutService {
    private static final double SHIPPING_FEE = 30.0;

    public static void checkout(Customer customer, Cart cart) {
        List<CartItem> items = cart.getItems();

        if (items.isEmpty()) {
            System.out.println("Error: Cart is empty.");
            return;
        }

        for (CartItem item : items) {
            Product product = item.getProduct();

            if (product instanceof Expirable && ((Expirable) product).isExpired()) {
                System.out.println("Error: Product " + product.getName() + " is expired.");
                return;
            }

            if (item.getQuantity() > product.getQuantity()) {
                System.out.println("Error: Not enough stock for " + product.getName());
                return;
            }
        }

        double subtotal = cart.calculateSubtotal();
        List<Shippable> shippableItems = cart.getShippableItems();
        double shipping = shippableItems.isEmpty() ? 0.0 : SHIPPING_FEE;
        double total = subtotal + shipping;

        if (customer.getBalance() < total) {
            System.out.println("Error: Insufficient balance.");
            return;
        }

        for (CartItem item : items) {
            item.getProduct().decreaseQuantity(item.getQuantity());
        }
        customer.decreaseBalance(total);

        if (!shippableItems.isEmpty()) {
            ShippingService.ship(shippableItems);
        }

        System.out.println("** Checkout receipt **");
        for (CartItem item : items) {
            System.out.println(item.getQuantity() + "x " + item.getProduct().getName() + " " + item.getTotalPrice());
        }
        System.out.println("----------------------");
        System.out.println("Subtotal " + subtotal);
        System.out.println("Shipping " + shipping);
        System.out.println("Amount " + total);
        System.out.println("Balance left " + customer.getBalance());
    }
}