package com.iordanvlad.rcf.receiptforge.service;

import com.iordanvlad.rcf.receiptforge.helper.ReceiptForgeHelper;
import com.iordanvlad.rcf.receiptforge.outputmodels.Item;
import com.iordanvlad.rcf.receiptforge.outputmodels.Receipt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ReceiptForgeService {

    private static final double VALUE_VARIATION_PERCENTAGE = 0.05; // 5% value variation
    private static final int DAYS_AGO = 30;
    private static final int MAX_ITEM_NUMBER = 30;
    private static final int MAX_ITEM_QUANTITY = 5;

    private final List<String> stores;
    private final Map<String, List<String>> productsByCategory;
    private final Map<String, Double> prices;
    private final ReceiptForgeHelper receiptForgeHelper;

    public ReceiptForgeService (ReceiptForgeHelper receiptForgeHelper) {
        this.receiptForgeHelper = receiptForgeHelper;
        this.productsByCategory = receiptForgeHelper.populateItems();
        this.prices = receiptForgeHelper.populatePrices();
        this.stores = receiptForgeHelper.populateStores();
    }

    public Receipt getReceipt () {
        Random random = new Random();

        String storeName = stores.get(random.nextInt(stores.size()));
        LocalDateTime purchaseDate = receiptForgeHelper.generateRandomDate(DAYS_AGO); // Generate random purchase date
        double totalSpent = 0; // Initialize total spent

        List<Item> items = new ArrayList<>();
        for (int i = 0; i < random.nextInt(MAX_ITEM_NUMBER) + 1; i++) {
            String category = receiptForgeHelper.getRandomCategory(productsByCategory);
            String productName = this.productsByCategory.get(category)
                    .get(random.nextInt(this.productsByCategory.get(category).size()));
            double price = receiptForgeHelper.getPriceWithVariation(prices.getOrDefault(productName, 1.0));

            int quantity = random.nextInt(MAX_ITEM_QUANTITY) + 1; // Random quantity, 1 or more
            double totalPrice = receiptForgeHelper.roundToTwoDecimals(price * quantity);
            totalSpent += totalPrice; // Accumulate total spent

            Item item = Item.builder()
                    .name(productName)
                    .price(price)
                    .totalPrice(totalPrice)
                    .quantity(quantity)
                    .category(category)
                    .build();
            items.add(item);
        }

        return Receipt.builder()
                .storeName(storeName)
                .purchaseDate(String.valueOf(purchaseDate))
                .totalSpent(totalSpent)
                .items(items)
                .build();
    }
}
