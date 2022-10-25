package com.khatrienterprises.trailinventory.documents;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author Ankit Khatri
 */
@Data
@Builder
@Document(collection = "products")
public class Product extends BaseDocument{

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String STYLE_CODE = "styleCode";
    public static final String SKU = "sku";
    public static final String SIZE = "size";
    public static final String COLOR = "color";
    public static final String AVAILABLE_QUANTITY = "availableQuantity";
    public static final String PRICE = "price";
    public static final String HSN_CODE = "hsnCode";
    public static final String CREATED_AT = "createdAt";
    public static final String UPDATED_AT = "updatedAt";
    public static final String VERSION = "version";


    private ObjectId id;
    private String name;
    private String styleCode;
    private String sku;
    private String size;
    private String color;
    private Long availableQuantity;
    private Double price;
    private String hsnCode;
    private List<String> images;
    public Long createdAt;
    protected Long updatedAt;
//    @Version
    protected Integer version;
}
