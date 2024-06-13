package com.acme.practica.pc2.sale.domain.services;

import com.acme.practica.pc2.sale.domain.model.aggregates.PurchaseOrder;
import com.acme.practica.pc2.sale.domain.model.queries.GetAllPurchaseOrdersQuery;
import com.acme.practica.pc2.sale.domain.model.queries.GetPurchaseOrdersByIdQuery;

import java.util.List;
import java.util.Optional;

public interface PurchaseOrderQueryService {
    List<PurchaseOrder> handle(GetAllPurchaseOrdersQuery query);
    Optional<PurchaseOrder> handle(GetPurchaseOrdersByIdQuery query);
}
