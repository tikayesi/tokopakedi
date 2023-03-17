package com.enigmacamp.tokopakedi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseDetailDTO {
    private String productName;
    private Integer quantity;
    private Integer priceSell;;
    private Integer subTotal;
}
