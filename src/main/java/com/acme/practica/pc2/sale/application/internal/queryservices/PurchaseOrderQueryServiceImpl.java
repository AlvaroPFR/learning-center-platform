package com.acme.practica.pc2.sale.application.internal.queryservices;

import com.acme.practica.pc2.sale.domain.model.aggregates.PurchaseOrder;
import com.acme.practica.pc2.sale.domain.model.queries.GetAllPurchaseOrdersQuery;
import com.acme.practica.pc2.sale.domain.model.queries.GetPurchaseOrdersByIdQuery;
import com.acme.practica.pc2.sale.domain.services.PurchaseOrderQueryService;
import com.acme.practica.pc2.sale.infrastructure.persistence.jpa.repositories.PurchaseOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderQueryServiceImpl implements PurchaseOrderQueryService {

    private final PurchaseOrderRepository purchaseOrderRepository;

    public PurchaseOrderQueryServiceImpl(PurchaseOrderRepository purchaseOrderRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    @Override
    public List<PurchaseOrder> handle(GetAllPurchaseOrdersQuery query) {
        return purchaseOrderRepository.findAll();

    }

    @Override
    public Optional<PurchaseOrder> handle(GetPurchaseOrdersByIdQuery query) {
        return purchaseOrderRepository.findById(query.id());
    }
}
