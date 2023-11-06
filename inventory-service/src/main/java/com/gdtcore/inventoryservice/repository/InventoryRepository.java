package com.gdtcore.inventoryservice.repository;

import com.gdtcore.inventoryservice.model.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {}
