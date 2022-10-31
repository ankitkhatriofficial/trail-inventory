package com.khatrienterprises.trailinventory.controller;

import com.khatrienterprises.trailinventory.service.ProductTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ankit Khatri
 */

@RestController
@RequestMapping("/trails/product-transactions")
public class ProductTransactionController {

    @Autowired private ProductTransactionService productTransactionService;

    @GetMapping("/get/{productId}")
    public ResponseEntity<?> getTransactionsOfProduct(@PathVariable("productId") String productId){
            return new ResponseEntity<>(productTransactionService.getTransactionsOfProduct(productId), HttpStatus.OK);
    }
}
