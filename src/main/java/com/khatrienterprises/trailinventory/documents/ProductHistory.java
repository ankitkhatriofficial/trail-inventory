package com.khatrienterprises.trailinventory.documents;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Ankit Khatri
 */

@Data
@Builder
@Document(collection = "product_history")
public class ProductHistory extends BaseDocument{

    private Integer userId;
    private Long createdAt;

    // TODO - create product history document fields
}
