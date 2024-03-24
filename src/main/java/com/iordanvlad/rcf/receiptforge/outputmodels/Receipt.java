package com.iordanvlad.rcf.receiptforge.outputmodels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Receipt {
    private String storeName;
    private String purchaseDate;
    private double totalSpent;
    private List<Item> items;
}


