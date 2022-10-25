package com.khatrienterprises.trailinventory.constants;

/**
 * @author Ankit Khatri
 */
public enum ExceptionCode {

    ONE_OR_MORE_VALIDATION_FAILURE(1000, "Validation failure");

    private int code;
    private String message;

    ExceptionCode(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() { return this.code; }

    public String getMessage(){ return this.getMessage(); }
}
