package com.khatrienterprises.trailinventory.service.impl;

import com.khatrienterprises.trailinventory.documents.Product;
import com.khatrienterprises.trailinventory.dto.ProductDto;
import com.khatrienterprises.trailinventory.repository.ProductRepository;
import com.khatrienterprises.trailinventory.service.ProductService;
import com.khatrienterprises.trailinventory.sro.ProductCreateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ankit Khatri
 */

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired private ProductRepository productRepository;

    @Override
    public void createNewProduct(ProductCreateRequest request) {
        log.info("Request received for creating new product: {}", request);
        Product product = Product.builder()
                .name(request.getName())
                .styleCode(request.getStyleCode())
                .sku(request.getSku())
                .size(request.getSize())
                .color(request.getColor())
                .availableQuantity(request.getAvailableQuantity())
                .price(request.getPrice())
                .hsnCode(request.getHsnCode())
                .images(request.getImages())
                .createdAt(new Date().getTime())
                .updatedAt(new Date().getTime())
                .build();
        productRepository.save(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> response =  products.stream().map(product ->{
            ProductDto productDto = ProductDto.builder()
                    .name(product.getName())
                    .styleCode(product.getStyleCode())
                    .sku(product.getSku())
                    .size(product.getSize())
                    .color(product.getColor())
                    .availableQuantity(product.getAvailableQuantity())
                    .price(product.getPrice())
                    .hsnCode(product.getHsnCode())
                    .images(product.getImages())
                    .build();
            return productDto;
        }).collect(Collectors.toList());
        return response;
    }
}
