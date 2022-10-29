package com.khatrienterprises.trailinventory.service.impl;

import com.khatrienterprises.trailinventory.constants.ExceptionCode;
import com.khatrienterprises.trailinventory.documents.Product;
import com.khatrienterprises.trailinventory.dto.ProductDto;
import com.khatrienterprises.trailinventory.exception.TrailException;
import com.khatrienterprises.trailinventory.repository.ProductRepository;
import com.khatrienterprises.trailinventory.service.ProductService;
import com.khatrienterprises.trailinventory.sro.ProductCreateRequest;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
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
        Product product = getProductBuilder(request)
                .createdAt(new Date().getTime()).build();
        productRepository.save(product);
    }

    private Product.ProductBuilder getProductBuilder(ProductCreateRequest request){
        Product.ProductBuilder productBuilder = Product.builder()
                .name(request.getName())
                .styleCode(request.getStyleCode())
                .sku(request.getSku())
                .size(request.getSize())
                .color(request.getColor())
                .availableQuantity(request.getAvailableQuantity())
                .price(request.getPrice())
                .hsnCode(request.getHsnCode())
                .images(request.getImages())
                .updatedAt(new Date().getTime());
        return productBuilder;
    }

    @Override
    public void updateProduct(String id, ProductCreateRequest request) {
        log.info("Request received to update product: {}, {}", id, request);
        Optional<Product> productOptional = productRepository.findById(new ObjectId(id));
        if(!productOptional.isPresent()){
            log.error("Product not found on updating operation: {}", id);
            throw new TrailException(ExceptionCode.PRODUCT_NOT_FOUND);
        }
        Product product = getProductBuilder(request)
                .id(new ObjectId(id)).build();
        productRepository.save(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> response =  products.stream().map(product -> getProductDtoBuilder(product).build()).collect(Collectors.toList());
        return response;
    }

    @Override
    public ProductDto getProductById(String id) {
        Optional<Product> productOptional = productRepository.findById(new ObjectId(id));
        if(productOptional.isPresent()){
            return getProductDtoBuilder(productOptional.get()).build();
        }
        return null;
    }

    private ProductDto.ProductDtoBuilder getProductDtoBuilder(Product product){
        ProductDto.ProductDtoBuilder productDtoBuilder = ProductDto.builder()
                .name(product.getName())
                .styleCode(product.getStyleCode())
                .sku(product.getSku())
                .size(product.getSize())
                .color(product.getColor())
                .availableQuantity(product.getAvailableQuantity())
                .price(product.getPrice())
                .hsnCode(product.getHsnCode())
                .images(product.getImages());
        return productDtoBuilder;
    }
}
