package com.enigmacamp.tokopakedi.service.impl;

import com.enigmacamp.tokopakedi.dto.PurchaseDTO;
import com.enigmacamp.tokopakedi.dto.PurchaseDetailDTO;
import com.enigmacamp.tokopakedi.entity.Product;
import com.enigmacamp.tokopakedi.entity.Purchase;
import com.enigmacamp.tokopakedi.entity.PurchaseDetail;
import com.enigmacamp.tokopakedi.repository.PurchaseDetailRepository;
import com.enigmacamp.tokopakedi.repository.PurchaseRepository;
import com.enigmacamp.tokopakedi.service.ProductService;
import com.enigmacamp.tokopakedi.service.PurchaseDetailService;
import com.enigmacamp.tokopakedi.service.PurchaseService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    PurchaseRepository purchaseRepository;

    PurchaseDetailService purchaseDetailService;

    ProductService productService;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository, PurchaseDetailService purchaseDetailService, ProductService productService) {
        this.purchaseRepository = purchaseRepository;
        this.purchaseDetailService = purchaseDetailService;
        this.productService = productService;
    }

    @Override
    public Purchase saveTransaction(Purchase purchase) {
        purchase.setPurchaseDate(Date.valueOf(LocalDate.now()));
        Purchase resultSavePurchase = purchaseRepository.save(purchase);

        for (PurchaseDetail pd: purchase.getPurchaseDetails()) {
            Product product = productService.getProductById(pd.getProduct().getId());
            product.setStock(product.getStock() - pd.getQuantity());
            pd.setPurchase(resultSavePurchase);
            pd.setProductTransactionPrice(product.getProductPrice());
            purchaseDetailService.savePurchaseDetail(pd);
        }

        return resultSavePurchase;
    }

    @Override
    public PurchaseDTO getPurchaseById(String id) {

        if (purchaseRepository.findById(id).isPresent()) {
            Purchase purchase = purchaseRepository.findById(id).get();
            PurchaseDTO purchaseDTO = new PurchaseDTO();
            purchaseDTO.setPurchaseId(purchase.getId());
            purchaseDTO.setCustomerName(purchase.getCustomer().getFullName());
            purchaseDTO.setPurchaseDate(purchase.getPurchaseDate());
            List<PurchaseDetailDTO> purchaseDetailDTOS = new ArrayList<>();

            Integer total = 0;
            for (PurchaseDetail pd : purchase.getPurchaseDetails()) {
                PurchaseDetailDTO purchaseDetailDTO = new PurchaseDetailDTO();
                purchaseDetailDTO.setProductName(pd.getProduct().getProductName());
                purchaseDetailDTO.setQuantity(pd.getQuantity());
                purchaseDetailDTO.setPriceSell(pd.getProductTransactionPrice());
                Integer subTotal = pd.getQuantity() * pd.getProductTransactionPrice();
                purchaseDetailDTO.setSubTotal(subTotal);
                total += subTotal;
                purchaseDetailDTOS.add(purchaseDetailDTO);
            }

            purchaseDTO.setTotal(total);
            purchaseDTO.setPurchaseDetailDTOs(purchaseDetailDTOS);


            return purchaseDTO;
        } else throw new NoSuchElementException();
    }

    @Override
    public Purchase getPurchaseByIdExample(String id) {
        return purchaseRepository.findById(id).get();
    }

}
