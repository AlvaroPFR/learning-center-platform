package com.acme.practica.pc2.sale.interfaces.rest.transform;

import com.acme.practica.pc2.sale.domain.model.commands.CreatePurchaseOrderCommand;
import com.acme.practica.pc2.sale.interfaces.rest.resources.CreatePurchaseOrderResource;

public class CreatePurchaseOrderCommandFromResourceAssembler {
    public static CreatePurchaseOrderCommand toCommandFromResource(CreatePurchaseOrderResource resource){
        return new CreatePurchaseOrderCommand(resource.customer(), resource.fabricId(), resource.country(), resource.resumeUrl(), resource.quantity());
    }
}
