package com.khatrienterprises.trailinventory.exception;

import com.khatrienterprises.trailinventory.Constants.ExceptionCode;
import lombok.Data;

/**
 * @author Ankit Khatri
 */
@Data
public class TrailException extends RuntimeException{

    private Integer errCode;
    private String message;

    public TrailException(ExceptionCode exception){
        super(exception.getMessage());
        this.errCode = exception.getCode();
        this.message = exception.getMessage();
    }

    public TrailException(Integer code, String message){
        this.errCode = code;
        this.message = message;
    }
}
