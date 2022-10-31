package com.khatrienterprises.trailinventory.service;

import com.khatrienterprises.trailinventory.dto.ProductTransactionDto;

import java.util.List;

/**
 * @author Ankit Khatri
 */
public interface ProductTransactionService {
    List<ProductTransactionDto> getTransactionsOfProduct(String productId);
}
