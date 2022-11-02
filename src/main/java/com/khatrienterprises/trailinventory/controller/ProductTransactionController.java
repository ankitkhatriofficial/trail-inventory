package com.khatrienterprises.trailinventory.controller;

import com.khatrienterprises.trailinventory.constants.ExceptionCode;
import com.khatrienterprises.trailinventory.exception.TrailException;
import com.khatrienterprises.trailinventory.service.ProductTransactionService;
import com.khatrienterprises.trailinventory.sro.ProductTransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

/**
 * @author Ankit Khatri
 */

@RestController
@CrossOrigin
@RequestMapping("/trails/product-transactions")
public class ProductTransactionController {

    @Autowired private ProductTransactionService productTransactionService;

    @GetMapping("/get/{productId}")
    public ResponseEntity<?> getTransactionsOfProduct(@PathVariable("productId") String productId){
            return new ResponseEntity<>(productTransactionService.getTransactionsOfProduct(productId), HttpStatus.OK);
    }

    @PostMapping("/execute")
    public ResponseEntity<?> createNewProductTransaction(@Valid @RequestBody ProductTransactionRequest request, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            String message = "Validation failed in fields:".concat(bindingResult.getFieldErrors().stream().map(fe->fe.getField()).collect(Collectors.joining(",")));
            throw new TrailException(ExceptionCode.ONE_OR_MORE_VALIDATION_FAILURE.getCode(),message);
        }
        productTransactionService.executeTransaction(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
