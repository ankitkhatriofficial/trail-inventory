package com.khatrienterprises.trailinventory.sro;

/**
 * @author Ankit Khatri
 */

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private Integer errCode;
    private String message;
}
