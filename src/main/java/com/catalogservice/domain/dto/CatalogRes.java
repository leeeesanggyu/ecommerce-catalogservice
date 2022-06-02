package com.catalogservice.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CatalogRes {

    private String productId;
    private String productName;
    private Integer stock;
    private Integer unitPrice;
    private Date createAt;
}
