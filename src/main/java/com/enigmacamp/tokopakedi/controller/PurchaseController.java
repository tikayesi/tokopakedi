package com.enigmacamp.tokopakedi.controller;

import com.enigmacamp.tokopakedi.dto.PurchaseDTO;
import com.enigmacamp.tokopakedi.entity.Purchase;
import com.enigmacamp.tokopakedi.entity.PurchaseDetail;
import com.enigmacamp.tokopakedi.service.PurchaseDetailService;
import com.enigmacamp.tokopakedi.service.PurchaseService;
import org.springframework.web.bind.annotation.*;

@RestController
public class PurchaseController {

    PurchaseDetailService purchaseDetailService;
    PurchaseService purchaseService;

    public PurchaseController(PurchaseDetailService purchaseDetailService, PurchaseService purchaseService) {
        this.purchaseDetailService = purchaseDetailService;
        this.purchaseService = purchaseService;
    }

    @PostMapping("/purchase-details")
    public PurchaseDetail savePurchaseDetail(@RequestBody PurchaseDetail purchaseDetail){
        return purchaseDetailService.savePurchaseDetail(purchaseDetail);
    }

    @PostMapping("/purchases")
    public Purchase savePurchase(@RequestBody Purchase purchase){
        return purchaseService.saveTransaction(purchase);
    }

    @GetMapping("/purchases/{id}")
    public PurchaseDTO getPurchase(@PathVariable String id){
        return purchaseService.getPurchaseById(id);
    }

}
