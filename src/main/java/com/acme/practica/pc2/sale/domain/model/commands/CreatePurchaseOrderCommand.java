package com.acme.practica.pc2.sale.domain.model.commands;

public record CreatePurchaseOrderCommand(String customer, Integer fabricId, String country, String resumeUrl, Integer quantity) {
}
