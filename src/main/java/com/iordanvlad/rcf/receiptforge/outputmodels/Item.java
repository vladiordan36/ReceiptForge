package com.iordanvlad.rcf.receiptforge.outputmodels;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private String name;
    private double price;
    private double totalPrice;
    private int quantity;
    private String category;
}
