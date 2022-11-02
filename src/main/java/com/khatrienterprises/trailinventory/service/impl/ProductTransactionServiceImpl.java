package com.khatrienterprises.trailinventory.service.impl;

import com.khatrienterprises.trailinventory.documents.ProductTransaction;
import com.khatrienterprises.trailinventory.dto.ProductTransactionDto;
import com.khatrienterprises.trailinventory.repository.ProductTransactionRepository;
import com.khatrienterprises.trailinventory.service.ProductTransactionService;
import com.khatrienterprises.trailinventory.sro.ProductTransactionRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ankit Khatri
 */

@Service
@Slf4j
public class ProductTransactionServiceImpl implements ProductTransactionService {

    @Autowired private ProductTransactionRepository productTransactionRepository;

    @Override
    public List<ProductTransactionDto> getTransactionsOfProduct(String productId) {
         List<ProductTransaction> productTransactionList = productTransactionRepository.findByProductId(productId);
         List<ProductTransactionDto> productTransactionDtoList = productTransactionList.stream().map(productTransaction -> getProductTransactionDto(productTransaction))
                 .collect(Collectors.toList());
         return productTransactionDtoList;
    }

    private ProductTransactionDto getProductTransactionDto(ProductTransaction productTransaction){
        ProductTransactionDto.ProductTransactionDtoBuilder dtoBuilder = ProductTransactionDto.builder()
                .userId(productTransaction.getUserId())
                .productId(productTransaction.getProductId())
                .createdAt(productTransaction.getCreatedAt())
                .price(productTransaction.getPrice())
                .transactionType(productTransaction.getTransactionType())
                .updatedQuantity(productTransaction.getUpdatedQuantity());
        return dtoBuilder.build();
    }

    // TODO: implement execution of product transaction
    @Override
    public void executeTransaction(ProductTransactionRequest request) {

    }
}
