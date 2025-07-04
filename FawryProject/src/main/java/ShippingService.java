import java.util.*;
public class ShippingService {
    public static void ship(List<Shippable> items){
        if (items == null || items.isEmpty()){
            System.out.println("No items to ship.");
            return;
        }
        System.out.println("** Shipment notice **");
        double totalWeight = 0.0;
        List<String> names = new ArrayList<>();
        List<Double> weights = new ArrayList<>();
        List<Integer> counts = new ArrayList<>();

        for (Shippable item : items) {
            String name = item.getName();
            double weight = item.getWeight();
            boolean found = false;

            for (int i = 0; i < names.size(); i++) {
                if (names.get(i).equals(name) && weights.get(i) == weight) {
                    counts.set(i, counts.get(i) + 1);
                    found = true;
                    break;
                }
            }
            if (!found) {
                names.add(name);
                weights.add(weight);
                counts.add(1);
            }

           totalWeight += weight;
        }
        for (int i = 0; i < names.size(); i++) {
            int count = counts.get(i);
            String name = names.get(i);
            double weight = weights.get(i);

            int weightInGrams = (int) (weight * 1000);
            System.out.println(count + "x " + name + " " + weightInGrams + "g");
        }

        System.out.printf("Total package weight %.1fkg%n", totalWeight);
    }
}
