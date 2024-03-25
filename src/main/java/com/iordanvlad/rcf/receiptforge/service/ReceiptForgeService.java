package com.iordanvlad.rcf.receiptforge.service;

import com.iordanvlad.rcf.receiptforge.helper.ReceiptFileWriter;
import com.iordanvlad.rcf.receiptforge.helper.ReceiptForgeHelper;
import com.iordanvlad.rcf.receiptforge.outputmodels.Item;
import com.iordanvlad.rcf.receiptforge.outputmodels.Receipt;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class ReceiptForgeService {
    private static final int DAYS_AGO = 365;
    private static final int MAX_ITEM_NUMBER = 30;
    private static final int MAX_ITEM_QUANTITY = 5;
    private static final int MAX_RECEIPTS = 100;
    private final Map<String, String> stores;
    private final Map<String, List<String>> productsByCategory;
    private final Map<String, Double> prices;
    private final ReceiptForgeHelper receiptForgeHelper;

    public ReceiptForgeService(ReceiptForgeHelper receiptForgeHelper) {
        this.receiptForgeHelper = receiptForgeHelper;
        this.productsByCategory = receiptForgeHelper.populateItems();
        this.prices = receiptForgeHelper.populatePrices();
        this.stores = receiptForgeHelper.populateStores();
    }

    public Receipt getReceipt() {
        Random random = new Random();

        String storeName = receiptForgeHelper.getRandomStore(stores);
        String storeAddress = stores.get(storeName);
        LocalDateTime purchaseDate = receiptForgeHelper.generateRandomDate(DAYS_AGO);

        double totalSpent = 0.0;
        int totalQuantity = 0;
        List<Item> items = new ArrayList();

        for (int i = 0; i < random.nextInt(MAX_ITEM_NUMBER) + 1; ++i) {
            String category = receiptForgeHelper.getRandomCategory(productsByCategory);
            String productName = productsByCategory.get(category).get(random.nextInt(productsByCategory.get(category).size()));

            double price = receiptForgeHelper.getPriceWithVariation(prices.getOrDefault(productName, 1.0));
            int quantity = random.nextInt(MAX_ITEM_QUANTITY) + 1;
            double totalPrice = receiptForgeHelper.roundToTwoDecimals(price * quantity);

            totalSpent += totalPrice;
            totalQuantity++;

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
                .storeAddress(storeAddress)
                .purchaseDate(String.valueOf(purchaseDate))
                .totalSpent(receiptForgeHelper.roundToTwoDecimals(totalSpent))
                .totalQuantity(totalQuantity)
                .items(items).build();
    }

    public String generateBulkReceiptsInFiles() {
        for (int i = 0; i < MAX_RECEIPTS; ++i) {
            Receipt receipt = getReceipt();
            String filePath = "E:\\Facultate\\Licenta\\data\\" + receipt.getPurchaseDate().hashCode() + ".json";
            ReceiptFileWriter receiptFileWriter = new ReceiptFileWriter();
            receiptFileWriter.writeReceiptToFile(receipt, filePath);
        }
        return "Success";
    }
}
