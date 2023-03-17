package com.enigmacamp.tokopakedi.service.impl;

import com.enigmacamp.tokopakedi.entity.PurchaseDetail;
import com.enigmacamp.tokopakedi.repository.PurchaseDetailRepository;
import com.enigmacamp.tokopakedi.service.PurchaseDetailService;
import org.springframework.stereotype.Service;

@Service
public class PurchaseDetailServiceImpl implements PurchaseDetailService {

    PurchaseDetailRepository purchaseDetailRepository;

    public PurchaseDetailServiceImpl(PurchaseDetailRepository purchaseDetailRepository) {
        this.purchaseDetailRepository = purchaseDetailRepository;
    }

    @Override
    public PurchaseDetail savePurchaseDetail(PurchaseDetail purchaseDetail) {
        return purchaseDetailRepository.save(purchaseDetail);
    }
}
