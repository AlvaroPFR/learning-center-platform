package com.acme.practica.pc2.sale.domain.services;

import com.acme.practica.pc2.sale.domain.model.commands.CreatePurchaseOrderCommand;

public interface PurchaseOrderCommandService {
    Integer handle(CreatePurchaseOrderCommand command);
}