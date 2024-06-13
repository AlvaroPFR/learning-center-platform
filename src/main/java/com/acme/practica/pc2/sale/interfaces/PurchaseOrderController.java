package com.acme.practica.pc2.sale.interfaces;


import com.acme.practica.pc2.sale.domain.model.aggregates.PurchaseOrder;
import com.acme.practica.pc2.sale.domain.model.queries.GetAllPurchaseOrdersQuery;
import com.acme.practica.pc2.sale.domain.model.queries.GetPurchaseOrdersByIdQuery;
import com.acme.practica.pc2.sale.domain.services.PurchaseOrderCommandService;
import com.acme.practica.pc2.sale.domain.services.PurchaseOrderQueryService;
import com.acme.practica.pc2.sale.interfaces.rest.resources.CreatePurchaseOrderResource;
import com.acme.practica.pc2.sale.interfaces.rest.resources.PurchaseOrderResource;
import com.acme.practica.pc2.sale.interfaces.rest.transform.CreatePurchaseOrderCommandFromResourceAssembler;
import com.acme.practica.pc2.sale.interfaces.rest.transform.PurchaseOrderResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/v1/purchaseorder", produces = APPLICATION_JSON_VALUE)
@Tag(name = "PurchaseOrder", description = "PurchaseOrder management endpoint")
public class PurchaseOrderController {
    private final PurchaseOrderQueryService purchaseOrderQueryService;
    private final PurchaseOrderCommandService purchaseOrderCommandService;

    public PurchaseOrderController(PurchaseOrderQueryService purchaseOrderQueryService, PurchaseOrderCommandService purchaseOrderCommandService) {
        this.purchaseOrderQueryService = purchaseOrderQueryService;
        this.purchaseOrderCommandService = purchaseOrderCommandService;
    }


    @PostMapping
    public ResponseEntity<PurchaseOrderResource> createPurchaseOrder(@RequestBody CreatePurchaseOrderResource createPurchaseOrderResource){
        var createPurchaseOrderCommand = CreatePurchaseOrderCommandFromResourceAssembler.toCommandFromResource(createPurchaseOrderResource);
        var purchaseOrderId = purchaseOrderCommandService.handle(createPurchaseOrderCommand);
        if (purchaseOrderId == 0){
            return ResponseEntity.badRequest().build();
        }
        var getPurchaseOrderByIdQuery = new GetPurchaseOrdersByIdQuery(purchaseOrderId);
        var purchaseOrder = purchaseOrderQueryService.handle(getPurchaseOrderByIdQuery);
        if (purchaseOrder.isEmpty())return ResponseEntity.badRequest().build();
        var purchaseOrderResource = PurchaseOrderResourceFromEntityAssembler.toResourceFromEntity(purchaseOrder.get());
        return new ResponseEntity<>(purchaseOrderResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PurchaseOrderResource>> getAllPurchaseOrders(){
        var getAllPurchaseOrdersQuery = new GetAllPurchaseOrdersQuery();
        var purchaseOrders = purchaseOrderQueryService.handle(getAllPurchaseOrdersQuery);
        var purchaseOrderResources = purchaseOrders.stream().map(PurchaseOrderResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(purchaseOrderResources);
    }

    @GetMapping("/{purchaseOrderId}")
    public ResponseEntity<PurchaseOrderResource> getPurchaseOrderById(@PathVariable Integer purchaseOrderId){
        var getPurchaseOrdersByIdQuery = new GetPurchaseOrdersByIdQuery(purchaseOrderId);
        var purchaseOrders = purchaseOrderQueryService.handle(getPurchaseOrdersByIdQuery);
        if (purchaseOrders.isEmpty()) return ResponseEntity.badRequest().build();
        var purchaseOrderResource = PurchaseOrderResourceFromEntityAssembler.toResourceFromEntity(purchaseOrders.get());
        return ResponseEntity.ok(purchaseOrderResource);
    }
}
