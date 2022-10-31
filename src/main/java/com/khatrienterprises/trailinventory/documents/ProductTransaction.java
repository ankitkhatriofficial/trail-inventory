package com.khatrienterprises.trailinventory.documents;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Ankit Khatri
 */

@Data
@Builder
@Document(collection = "product_transaction_history")
public class ProductTransaction extends BaseDocument{

    @Id
    private ObjectId id;
    private String userId;
    private String productId;
    private Long createdAt;
    private Double price;
    private TransactionType transactionType;
    private Long updatedQuantity;

    // TODO - create product history document fields

    public enum TransactionType{
        SALE("sale"),
        PURCHASE("purchase"),
        CORRECTION("correction");
        private String type;
        TransactionType(String type){
            this.type = type;
        }
    }
}
