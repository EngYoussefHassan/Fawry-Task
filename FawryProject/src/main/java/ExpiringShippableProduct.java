import java.time.LocalDate;

public class ExpiringShippableProduct extends Product implements Expirable, Shippable {
    private LocalDate expiryDate;
    private double weight;

    public ExpiringShippableProduct(String name, double price, int quantity, double weight, String expiryDateStr) {
        super(name, price, quantity);
        this.weight = weight;
        this.expiryDate = LocalDate.parse(expiryDateStr);
    }

    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public double getWeight() {
        return weight;
    }
}