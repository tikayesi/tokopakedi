package com.enigmacamp.tokopakedi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "trx_purchase_detail")
@Getter
@Setter
@NoArgsConstructor
public class PurchaseDetail {
    @Id
    @Column(name = "purchase_detail_id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;

    private Integer productTransactionPrice;

    @ManyToOne
    @JoinColumn(name = "purchase_id")
    @JsonIgnoreProperties("purchaseDetails")
    private Purchase purchase;
}
