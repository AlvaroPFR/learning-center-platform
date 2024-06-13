package com.acme.practica.pc2.sale.infrastructure.persistence.jpa.repositories;

import com.acme.practica.pc2.sale.domain.model.aggregates.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {
    boolean existsByCustomer(String customer);
}
