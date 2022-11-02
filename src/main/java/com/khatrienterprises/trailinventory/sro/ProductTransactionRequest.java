package com.khatrienterprises.trailinventory.sro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.khatrienterprises.trailinventory.documents.ProductTransaction;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author Ankit Khatri
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductTransactionRequest extends BaseSro{

    @NotEmpty
    private String productId;

    private ProductTransaction.TransactionType transactionType;

    private Long quantity;

    private Double price;
}
