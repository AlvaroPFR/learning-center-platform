package com.acme.practica.pc2.sale.domain.exceptions;

public class PurchaseOrderNotFoundException extends RuntimeException{
    public PurchaseOrderNotFoundException(Long aLong){
        super("Product with id" + aLong + " not found");
    }
}
