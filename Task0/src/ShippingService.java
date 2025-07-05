import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides functionality for handling product shipments.
 * <p>
 * This service simulates shipping by printing shipment details to the console,
 * including item counts, their weight and total weights.
 */
public class ShippingService {
    /**
     * The flat shipping cost applied when shipping any items.
     */
    public static final double shippingCost = 30;

    /**
     * Formats the weight of a product line or total shipment.
     * Converts values to grams (g) or kilograms (kg) depending on size.
     * If product size >= 1000g will be converted to kg.
     *
     * @param weight   the weight per item (in grams)
     * @param quantity the quantity of items
     * @return formatted weight string (e.g., "500g", "1.25kg")
     */
    private static String getWeight(double weight, int quantity) {
        double total = weight * quantity;
        if (total >= 1000) {
            return String.format("%.2fkg", total / 1000);
        } else {
            return String.format("%.0fg", total);
        }
    }

    /**
     * Ships a list of {@link Shippable} items.
     * <p>
     * This method prints a shipment notice to the console, showing the count and weight
     * of each distinct product, as well as the total weight of the shipment.
     *
     * @param items the list of shippable items to be shipped
     * @return Shipping cost
     */
    public static double ship(List<Shippable> items) {
        if (items.isEmpty()) return 0;
        double totalWeight = 0;

        Map<Shippable, Integer> itemCount = new HashMap<>();

        for (Shippable item : items) {
            itemCount.put(item, itemCount.getOrDefault(item, 0) + 1);
            totalWeight += item.getWeight();
        }



        System.out.println("** Shipment notice **");
        for (Map.Entry<Shippable, Integer> entry : itemCount.entrySet()) {
            System.out.printf("%-20s %10s\n", entry.getValue() + "x " + entry.getKey().getName(), getWeight(entry.getKey().getWeight(), entry.getValue()));
        }
        System.out.printf("%-20s %10s\n", "Total package weight", getWeight(totalWeight, 1));

        if(totalWeight == 0) return 0;
        return shippingCost;
    }
}
