package com.enigmacamp.tokopakedi.dto;

import com.enigmacamp.tokopakedi.entity.PurchaseDetail;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class PurchaseDTO {
    private String purchaseId;
    private String customerName;
    private Integer total;
    private Date purchaseDate;
    private List<PurchaseDetailDTO> purchaseDetailDTOs;
}
