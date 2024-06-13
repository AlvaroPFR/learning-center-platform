package com.acme.practica.pc2.sale.application.internal.commandservices;

import com.acme.practica.pc2.sale.domain.model.aggregates.PurchaseOrder;
import com.acme.practica.pc2.sale.domain.model.commands.CreatePurchaseOrderCommand;
import com.acme.practica.pc2.sale.domain.services.PurchaseOrderCommandService;
import com.acme.practica.pc2.sale.infrastructure.persistence.jpa.repositories.PurchaseOrderRepository;
import org.springframework.stereotype.Service;

@Service
public class PurchaseOrderCommandServiceImpl implements PurchaseOrderCommandService{

    private final PurchaseOrderRepository purchaseOrderRepository;

    public PurchaseOrderCommandServiceImpl(PurchaseOrderRepository purchaseOrderRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
    }


    @Override
    public Integer handle(CreatePurchaseOrderCommand command) {
        if (purchaseOrderRepository.existsByCustomer(command.customer())) {
            throw new IllegalArgumentException("Order with same customer name already exists");
        }
        var purchaseOrder = new PurchaseOrder(command);
        try {
            purchaseOrderRepository.save(purchaseOrder);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving details: " + e.getMessage());
        }
        return purchaseOrder.getId();
    }
}
