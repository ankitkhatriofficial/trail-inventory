package com.khatrienterprises.trailinventory.controller;

import com.khatrienterprises.trailinventory.constants.ExceptionCode;
import com.khatrienterprises.trailinventory.dto.ProductDto;
import com.khatrienterprises.trailinventory.exception.TrailException;
import com.khatrienterprises.trailinventory.service.ProductService;
import com.khatrienterprises.trailinventory.sro.ProductCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ankit Khatri
 */

@RestController
@CrossOrigin
@RequestMapping("/trail/products")
public class ProductsController {

    @Autowired private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<?> createNewProduct(@Valid @RequestBody ProductCreateRequest request, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            String message = "Validation failed in fields:".concat(bindingResult.getFieldErrors().stream().map(fe->fe.getField()).collect(Collectors.joining(",")));
            throw new TrailException(ExceptionCode.ONE_OR_MORE_VALIDATION_FAILURE.getCode(),message);
        }
        productService.createNewProduct(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") String id, @Valid @RequestBody ProductCreateRequest request, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            String message = "Validation failed in fields:".concat(bindingResult.getFieldErrors().stream().map(fe->fe.getField()).collect(Collectors.joining(",")));
            throw new TrailException(ExceptionCode.ONE_OR_MORE_VALIDATION_FAILURE.getCode(),message);
        }
        productService.updateProduct(id, request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //TODO add pagination
    @GetMapping("/get-all-products")
    public ResponseEntity<List<ProductDto>> getAllProducts(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                           @RequestParam(value = "size", required = false, defaultValue = "25") Integer size,
                                                           @RequestParam(value = "sortBy", required = false, defaultValue = "date") String sortBy,
                                                           @RequestParam(value = "sortOrder", required = false, defaultValue = "-1") Integer sortOrder,
                                                           @RequestParam(value = "searchText", required = false) String searchText){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getAllProducts(@PathVariable("id") String id){
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProductsById(@RequestBody List<String> productIds){
        productService.deleteByIds(productIds);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
