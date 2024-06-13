package com.acme.practica.pc2.sale.interfaces.rest.transform;

import com.acme.practica.pc2.sale.domain.model.aggregates.PurchaseOrder;
import com.acme.practica.pc2.sale.interfaces.rest.resources.PurchaseOrderResource;

public class PurchaseOrderResourceFromEntityAssembler {
    public static PurchaseOrderResource toResourceFromEntity(PurchaseOrder entity){
        return new PurchaseOrderResource(entity.getId(), entity.getCustomer(), entity.getFabricId(), entity.getCountry(), entity.getResumeUrl(), entity.getQuantity());
    }
}
