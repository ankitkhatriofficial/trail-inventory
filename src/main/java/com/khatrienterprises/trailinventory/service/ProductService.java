package com.khatrienterprises.trailinventory.service;

import com.khatrienterprises.trailinventory.dto.ProductDto;
import com.khatrienterprises.trailinventory.sro.ProductCreateRequest;

import java.util.List;

/**
 * @author Ankit Khatri
 */
public interface ProductService {
    void createNewProduct(ProductCreateRequest request);
    void updateProduct(String id, ProductCreateRequest request);
    List<ProductDto> getAllProducts();
    ProductDto getProductById(String id);
}
