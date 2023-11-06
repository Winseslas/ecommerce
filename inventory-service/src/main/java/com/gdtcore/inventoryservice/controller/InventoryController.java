package com.gdtcore.inventoryservice.controller;

import com.gdtcore.inventoryservice.model.dto.InventoryRequest;
import com.gdtcore.inventoryservice.model.dto.InventoryResponse;
import com.gdtcore.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("read-all")
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> getAllInventories() {
        return this.inventoryService.getAllInventories();
    }

    @GetMapping("/read-one/{id}")
    public ResponseEntity<InventoryResponse> getInventoryById(@PathVariable Long id) {
        return new ResponseEntity<>(inventoryService.getInventoryById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<InventoryResponse> createInventory(@RequestBody InventoryRequest inventoryRequest) {
        InventoryResponse inventoryResponse = inventoryService.createInventory(inventoryRequest);
        return new ResponseEntity<InventoryResponse>(inventoryResponse, HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<InventoryResponse> updateInventory(@PathVariable Long id, @RequestBody InventoryRequest inventoryRequest) {
        InventoryResponse inventoryResponse = this.inventoryService.updateInventory(id, inventoryRequest);
        return new ResponseEntity<InventoryResponse>(inventoryResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable Long id) {
        this.inventoryService.deleteInventory(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
