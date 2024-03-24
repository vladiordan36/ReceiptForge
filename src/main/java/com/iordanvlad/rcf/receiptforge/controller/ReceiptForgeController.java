package com.iordanvlad.rcf.receiptforge.controller;

import com.iordanvlad.rcf.receiptforge.outputmodels.Receipt;
import com.iordanvlad.rcf.receiptforge.service.ReceiptForgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/receipt-forge")
public class ReceiptForgeController {

    private final ReceiptForgeService receiptForgeService;

    @Autowired
    public ReceiptForgeController (ReceiptForgeService receiptForgeService) {
        this.receiptForgeService = receiptForgeService;
    }

    @GetMapping("/get-receipt")
    public ResponseEntity<Receipt> getMockReceipt () {
        return ResponseEntity.ok(receiptForgeService.getReceipt());

    }
}

