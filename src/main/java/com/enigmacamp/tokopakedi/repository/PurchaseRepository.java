package com.enigmacamp.tokopakedi.repository;

import com.enigmacamp.tokopakedi.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, String> {
}
