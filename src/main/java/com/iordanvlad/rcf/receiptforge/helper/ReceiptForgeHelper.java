package com.iordanvlad.rcf.receiptforge.helper;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

@Component
public class ReceiptForgeHelper {

    private static final double VALUE_VARIATION_PERCENTAGE = 0.05;
    public Map<String, Double> populatePrices () {
        Map<String, Double> prices = new HashMap<>();

        // Hardcoded item prices
        prices.put("Milk", 2.50);
        prices.put("Bread", 1.75);
        prices.put("Eggs", 1.99);
        prices.put("Butter", 3.50);
        prices.put("Sugar", 1.25);
        prices.put("Salt", 0.99);
        prices.put("Rice", 2.00);
        prices.put("Pasta", 1.50);
        prices.put("Flour", 1.00);
        prices.put("Vegetables", 2.25);
        prices.put("Fruits", 2.75);
        prices.put("Meat", 5.00);
        prices.put("Fish", 4.50);
        prices.put("Cheese", 3.75);
        prices.put("Yogurt", 1.99);
        prices.put("Cereal", 3.25);
        prices.put("Snacks", 2.25);
        prices.put("Dish soap", 2.25);
        prices.put("Laundry detergent", 4.99);
        prices.put("Toilet paper", 3.50);
        prices.put("Paper towels", 2.75);
        prices.put("Trash bags", 3.00);
        prices.put("Bathroom cleaner", 2.50);
        prices.put("Kitchen cleaner", 2.50);
        prices.put("Glass cleaner", 2.25);
        prices.put("All-purpose cleaner", 2.75);
        prices.put("Floor cleaner", 3.00);
        prices.put("Sponges", 1.50);
        prices.put("Dishwasher detergent", 4.25);
        prices.put("Fabric softener", 3.75);
        prices.put("Bleach", 2.00);

        return prices;
    }

    public Map<String, List<String>> populateItems () {
        Map<String, List<String>> products = new HashMap<>();
        products.put("Food", Arrays.asList("Milk", "Bread", "Eggs", "Butter", "Sugar", "Salt", "Rice", "Pasta", "Flour", "Vegetables", "Fruits", "Meat", "Fish", "Cheese", "Yogurt", "Cereal", "Snacks"));
        products.put("Household", Arrays.asList("Dish soap", "Laundry detergent", "Toilet paper", "Paper towels", "Trash bags", "Bathroom cleaner", "Kitchen cleaner", "Glass cleaner", "All-purpose cleaner", "Floor cleaner", "Sponges", "Dishwasher detergent", "Fabric softener", "Bleach"));
        return products;

    }

    public List<String> populateStores () {
        return Arrays.asList("Carrefour", "Kaufland", "Auchan", "Mega Image", "Lidl", "Profi", "Penny Market",
                "Selgros", "Metro Cash & Carry", "Cora");
    }


    public LocalDateTime generateRandomDate (int daysAgo) {
        Random random = new Random();
        long minDay = LocalDateTime.now().minusDays(daysAgo).toEpochSecond(ZoneOffset.UTC);
        long maxDay = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        long randomDay = minDay + random.nextInt((int) (maxDay - minDay));
        return LocalDateTime.ofEpochSecond(randomDay, 0, ZoneOffset.UTC);
    }

    public double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    public double getPriceWithVariation(double price){
        Random random = new Random();
        double priceVariation = price * VALUE_VARIATION_PERCENTAGE;
        return roundToTwoDecimals(price + (random.nextBoolean() ? priceVariation : -priceVariation));
    }

    public String getRandomCategory (Map <String, List<String>> productsByCategory) {
        List<String> categories = new ArrayList<>(productsByCategory.keySet());
        Random random = new Random();
        return categories.get(random.nextInt(categories.size()));
    }
}
