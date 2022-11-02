package com.khatrienterprises.trailinventory.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author Ankit Khatri
 */
@Data
@Builder
public class ProductDto extends BaseDto{

    private String id;
    private String name;
    private String styleCode;
    private String sku;
    private String size;
    private String color;
    private Long availableQuantity;
    private Double price;
    private String hsnCode;
    private List<String> images;
}
