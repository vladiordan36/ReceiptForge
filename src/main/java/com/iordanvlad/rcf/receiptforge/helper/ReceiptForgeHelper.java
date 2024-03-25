package com.iordanvlad.rcf.receiptforge.helper;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class ReceiptForgeHelper {
    private static final double VALUE_VARIATION_PERCENTAGE = 0.05;

    public ReceiptForgeHelper() {
    }

    public Map<String, Double> populatePrices() {
        Map<String, Double> prices = new HashMap();
        prices.put("Milk", 2.5);
        prices.put("Bread", 1.75);
        prices.put("Eggs", 1.99);
        prices.put("Butter", 3.5);
        prices.put("Sugar", 1.25);
        prices.put("Salt", 0.99);
        prices.put("Rice", 2.0);
        prices.put("Pasta", 1.5);
        prices.put("Flour", 1.0);
        prices.put("Vegetables", 2.25);
        prices.put("Fruits", 2.75);
        prices.put("Meat", 5.0);
        prices.put("Fish", 4.5);
        prices.put("Cheese", 3.75);
        prices.put("Yogurt", 1.99);
        prices.put("Cereal", 3.25);
        prices.put("Snacks", 2.25);
        prices.put("Dish soap", 2.25);
        prices.put("Laundry detergent", 4.99);
        prices.put("Toilet paper", 3.5);
        prices.put("Paper towels", 2.75);
        prices.put("Trash bags", 3.0);
        prices.put("Bathroom cleaner", 2.5);
        prices.put("Kitchen cleaner", 2.5);
        prices.put("Glass cleaner", 2.25);
        prices.put("All-purpose cleaner", 2.75);
        prices.put("Floor cleaner", 3.0);
        prices.put("Sponges", 1.5);
        prices.put("Dishwasher detergent", 4.25);
        prices.put("Fabric softener", 3.75);
        prices.put("Bleach", 2.0);
        return prices;
    }

    public Map<String, List<String>> populateItems() {
        Map<String, List<String>> products = new HashMap();

        products.put("Food", Arrays.asList("Milk", "Bread", "Eggs", "Butter", "Sugar", "Salt", "Rice", "Pasta", "Flour",
                "Vegetables", "Fruits", "Meat", "Fish", "Cheese", "Yogurt", "Cereal", "Snacks"));
        products.put("Household", Arrays.asList("Dish soap", "Laundry detergent", "Toilet paper", "Paper towels",
                "Trash bags", "Bathroom cleaner", "Kitchen cleaner", "Glass cleaner", "All-purpose cleaner",
                "Floor cleaner", "Sponges", "Dishwasher detergent", "Fabric softener", "Bleach"));
        products.put("Self care", Arrays.asList("Shampoo", "Shower gel", "Conditioner", "Cotton pads", "Toothpaste",
                "Toothbrush", "Q-tips", "Shaving cream", "Shaving blades", "Aftershave"));

        return products;
    }

    public Map<String, String> populateStores() {
        Map<String, String> stores = new HashMap();
        stores.put("Carrefour", "Centrul Comercial Era, Șoseaua Păcurari 121, Iași 700522");
        stores.put("Kaufland", "Șoseaua Păcurari 86M, Iași 700547");
        stores.put("Auchan", "nr. 5C, Palas Mall, Strada Palas, Iași 700051");
        stores.put("Mega Image", "Bulevardul Ștefan cel Mare și Sfânt 9, Iași 700498");
        stores.put("Lidl", "Strada Tabacului 7A, Iași 700441");
        stores.put("Profi", "Șoseaua Păcurari 138, Iași 700545");
        stores.put("Penny Market", "Strada Cuza Vodă 36-38, Valea Lupului 707410");
        stores.put("Selgros", "Șos. Nicolina nr. 57A, Iași 700711");
        stores.put("Metro Cash & Carry", "Strada Brătuleni, Comuna Miroslava, Nr. 7, judet Iasi Bratuleni, 707305");
        return stores;
    }

    public LocalDateTime generateRandomDate(int daysAgo) {
        Random random = new Random();
        long minDay = LocalDateTime.now().minusDays((long) daysAgo).toEpochSecond(ZoneOffset.UTC);
        long maxDay = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        long randomDay = minDay + (long) random.nextInt((int) (maxDay - minDay));
        return LocalDateTime.ofEpochSecond(randomDay, 0, ZoneOffset.UTC);
    }

    public double roundToTwoDecimals(double value) {
        return (double) Math.round(value * 100.0) / 100.0;
    }

    public double getPriceWithVariation(double price) {
        Random random = new Random();
        double priceVariation = price * 0.05;
        return this.roundToTwoDecimals(price + (random.nextBoolean() ? priceVariation : -priceVariation));
    }

    public String getRandomCategory(Map<String, List<String>> productsByCategory) {
        List<String> categories = new ArrayList(productsByCategory.keySet());
        Random random = new Random();
        return (String) categories.get(random.nextInt(categories.size()));
    }

    public String getRandomStore(Map<String, String> store) {
        List<String> stores = new ArrayList(store.keySet());
        Random random = new Random();
        return (String) stores.get(random.nextInt(stores.size()));
    }
}
