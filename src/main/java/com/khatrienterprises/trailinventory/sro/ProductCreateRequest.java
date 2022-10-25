package com.khatrienterprises.trailinventory.sro;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Ankit Khatri
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductCreateRequest extends BaseSro{

    @NotEmpty
    private String name;

    @NotEmpty
    private String styleCode;

    @NotEmpty
    private String sku;

    private String size;
    private String color;

    @NotNull
    private Long availableQuantity;
    private Double price;
    private String hsnCode;
    private List<String> images;

}
