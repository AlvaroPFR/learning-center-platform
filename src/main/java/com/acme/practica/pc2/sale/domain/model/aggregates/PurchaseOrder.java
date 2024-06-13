package com.acme.practica.pc2.sale.domain.model.aggregates;

import com.acme.practica.pc2.sale.domain.model.commands.CreatePurchaseOrderCommand;
import com.acme.practica.pc2.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

@Entity
public class PurchaseOrder extends AuditableModel {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    @Getter
    @Column(name = "customer", nullable = false, unique = true)
    private String customer;

    @Getter
    @Column(name = "fabricId", nullable = false, unique = true)
    private Integer fabricId;

    @Getter
    @Column(name = "country", nullable = false)
    private String country;

    @Getter
    @Column(name = "resumeUrl", nullable = false)
    private String resumeUrl;

    @Getter
    @Column(name = "quantity", nullable = false)
    private Integer quantity;


    public PurchaseOrder() {
        this.quantity = null;
        this.resumeUrl = Strings.EMPTY;
        this.country = Strings.EMPTY;
        this.fabricId = null;
        this.customer = Strings.EMPTY;
    }


    public PurchaseOrder(String customer, Integer fabricId, String country, String resumeUrl, Integer quantity) {
        this();
        this.customer = customer;
        this.fabricId = fabricId;
        this.country = country;
        this.resumeUrl = resumeUrl;
        this.quantity = quantity;
    }

    public PurchaseOrder(CreatePurchaseOrderCommand command){
        this();
        this.customer = command.customer();
        this.fabricId = command.fabricId();
        this.country = command.country();
        this.resumeUrl = command.resumeUrl();
        this.quantity = command.quantity();
    }
}
