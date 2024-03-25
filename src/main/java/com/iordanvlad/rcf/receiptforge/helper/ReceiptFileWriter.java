package com.iordanvlad.rcf.receiptforge.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iordanvlad.rcf.receiptforge.outputmodels.Receipt;

import java.io.FileWriter;
import java.io.IOException;

public class ReceiptFileWriter {

    private final ObjectMapper objectMapper;

    public ReceiptFileWriter() {
        this.objectMapper = new ObjectMapper();
    }

    public void writeReceiptToFile(Receipt receipt, String filePath) {
        try {
            // Convert Receipt object to JSON string
            String json = objectMapper.writeValueAsString(receipt);

            // Write JSON string to file
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(json);
            fileWriter.close();

            System.out.println("Receipt written to file: " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing receipt to file: " + e.getMessage());
        }
    }
}
