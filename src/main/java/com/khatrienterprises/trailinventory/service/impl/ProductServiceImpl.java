package com.khatrienterprises.trailinventory.service.impl;

import com.khatrienterprises.trailinventory.constants.ExceptionCode;
import com.khatrienterprises.trailinventory.documents.Product;
import com.khatrienterprises.trailinventory.dto.ProductDto;
import com.khatrienterprises.trailinventory.exception.TrailException;
import com.khatrienterprises.trailinventory.repository.ProductRepository;
import com.khatrienterprises.trailinventory.repository.ProductTransactionRepository;
import com.khatrienterprises.trailinventory.service.ProductService;
import com.khatrienterprises.trailinventory.sro.ProductCreateRequest;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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
    @Autowired private ProductTransactionRepository productTransactionRepository;

    @Override
    public void createNewProduct(ProductCreateRequest request) {
        log.info("Request received for creating new product: {}", request);
        Product product = buildProduct(request);
        product.setCreatedAt(product.getUpdatedAt());
        productRepository.save(product);
    }

    private Product buildProduct(ProductCreateRequest request){
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
        return productBuilder.build();
    }

    @Override
    public void updateProduct(String id, ProductCreateRequest request) {
        log.info("Request received to update product: {}, {}", id, request);
        ObjectId productId = new ObjectId(id);
        Optional<Product> productOptional = productRepository.findById(productId);
        if(!productOptional.isPresent()){
            log.error("Product not found on updating operation: {}", id);
            throw new TrailException(ExceptionCode.PRODUCT_NOT_FOUND);
        }
        Product product = buildProduct(request);
        product.setId(productId);
        productRepository.save(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> response =  products.stream().map(product -> getProductDto(product)).collect(Collectors.toList());
        return response;
    }

    @Override
    public ProductDto getProductById(String id) {
        Optional<Product> productOptional = productRepository.findById(new ObjectId(id));
        if(productOptional.isPresent()){
            return getProductDto(productOptional.get());
        }
        return null;
    }

    private ProductDto getProductDto(Product product){
        ProductDto.ProductDtoBuilder productDtoBuilder = ProductDto.builder()
                .id(product.getId().toHexString())
                .name(product.getName())
                .styleCode(product.getStyleCode())
                .sku(product.getSku())
                .size(product.getSize())
                .color(product.getColor())
                .availableQuantity(product.getAvailableQuantity())
                .price(product.getPrice())
                .hsnCode(product.getHsnCode())
                .images(product.getImages());
        return productDtoBuilder.build();
    }

    @Override
    @Transactional
    public void deleteByIds(List<String> productIds){
        if(!CollectionUtils.isEmpty(productIds)){
            List<ObjectId> objectIds = productIds.stream().map(productId -> new ObjectId(productId)).collect(Collectors.toList());
            productRepository.deleteAllById(objectIds);
            productTransactionRepository.deleteAllByProductId(productIds);
        }
    }
}
