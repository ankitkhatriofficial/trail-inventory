package com.khatrienterprises.trailinventory.exception;

import com.khatrienterprises.trailinventory.sro.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Ankit Khatri
 */
@RestControllerAdvice
@Slf4j
public class TrailExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {TrailException.class})
    public ResponseEntity<ErrorResponse> handlePaymentsException(TrailException ex, WebRequest request) {
        log.error("Request failed with code:{}, message:{}", ex.getErrCode(), ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrCode(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR) ;
    }
}
