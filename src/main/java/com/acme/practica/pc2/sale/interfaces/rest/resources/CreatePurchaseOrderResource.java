package com.acme.practica.pc2.sale.interfaces.rest.resources;

public record CreatePurchaseOrderResource(String customer, Integer fabricId, String country, String resumeUrl, Integer quantity) {
}
