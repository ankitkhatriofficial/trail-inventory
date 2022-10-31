package com.khatrienterprises.trailinventory.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.khatrienterprises.trailinventory.documents.ProductTransaction;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;

/**
 * @author Ankit Khatri
 */
@Data
@Builder
public class ProductTransactionDto extends BaseDto{

    private String userId;
    private String productId;
    private Long createdAt;
    private Double price;
    private ProductTransaction.TransactionType transactionType;
    private Long updatedQuantity;

}
