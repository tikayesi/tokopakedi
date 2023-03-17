package com.enigmacamp.tokopakedi.service;

import com.enigmacamp.tokopakedi.dto.PurchaseDTO;
import com.enigmacamp.tokopakedi.entity.Purchase;

public interface PurchaseService {
    Purchase saveTransaction(Purchase purchase);
    PurchaseDTO getPurchaseById(String id);

    Purchase getPurchaseByIdExample(String id);
}
