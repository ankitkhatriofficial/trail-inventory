package com.khatrienterprises.trailinventory.controller;

import com.khatrienterprises.trailinventory.Constants.ExceptionCode;
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

    //TODO add pagination
    @GetMapping("/get-all-products")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }
}
