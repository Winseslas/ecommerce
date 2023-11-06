package com.gdtcore.inventoryservice.service;

import com.gdtcore.inventoryservice.exceptions.InventoryNotFoundException;
import com.gdtcore.inventoryservice.model.dto.InventoryRequest;
import com.gdtcore.inventoryservice.model.dto.InventoryResponse;
import com.gdtcore.inventoryservice.model.entities.Inventory;
import com.gdtcore.inventoryservice.repository.InventoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Service
@Slf4j
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    /**
     * Retrieves all inventories.
     *
     * @return         	a list of InventoryResponse objects representing all inventories
     */
    public List<InventoryResponse> getAllInventories() {
        List<Inventory> allInventories = inventoryRepository.findAll();
        if (allInventories.isEmpty()) {
            throw new InventoryNotFoundException("No inventories found.");
        }
        return allInventories.stream().map(this::mapToInventoryResponse).toList();
    }


    /**
     * Retrieves the inventory information by the specified ID.
     *
     * @param  id  the ID of the inventory
     * @return     the inventory response containing the inventory information
     */
    public InventoryResponse getInventoryById(Long id){
        Inventory existingInventory = this.inventoryRepository.findById((long) Math.toIntExact(id))
                .orElseThrow(() -> new InventoryNotFoundException("Inventory not found with id: " + id));
        return mapToInventoryResponse(existingInventory);
    }

    /**
     * Creates an inventory based on the provided inventory request.
     *
     * @param  inventoryRequest  the inventory request containing the inventory details
     * @return                   the inventory response containing the created inventory details
     */
    public InventoryResponse createInventory(InventoryRequest inventoryRequest){
        Inventory inventory = mapToInventoryRequest(inventoryRequest);
        return mapToInventoryResponse(this.inventoryRepository.save(inventory));
    }

    public InventoryResponse updateInventory(Long id, InventoryRequest inventoryRequest) {
        Inventory existingInventory = this.inventoryRepository.findById((long) Math.toIntExact(id))
                .orElseThrow(() -> new InventoryNotFoundException("Inventory not found with id: " + id));

        if (inventoryRequest.getQuantity() != null) {
            existingInventory.setQuantity(inventoryRequest.getQuantity());
        }

        Inventory inventory = this.inventoryRepository.save(existingInventory);
        return mapToInventoryResponse(inventory);
    }

    /**
     * Deletes an inventory with the specified ID.
     *
     * @param  id  the ID of the inventory to delete
     * @return     void
     */
    public void deleteInventory(Long id){
        Inventory existingInventory = this.inventoryRepository.findById((long) Math.toIntExact(id))
                .orElseThrow(() -> new InventoryNotFoundException("Inventory not found with id: " + id));
        this.inventoryRepository.delete(existingInventory);
    }


    /**
     * Maps an Inventory object to an InventoryResponse object.
     *
     * @param  inventory  the Inventory object to be mapped
     * @return            the mapped InventoryResponse object
     */
    private InventoryResponse mapToInventoryResponse(Inventory inventory){
        return InventoryResponse.builder()
                .id(inventory.getId())
                .code(inventory.getCode())
                .quantity(inventory.getQuantity())
                .build();
    }

    /**
     * Maps an InventoryRequest object to an Inventory object.
     *
     * @param  inventoryRequest  the InventoryRequest object to be mapped
     * @return                   the mapped Inventory object
     */
    private Inventory mapToInventoryRequest(InventoryRequest inventoryRequest){
        return Inventory.builder()
                .code(UUID.randomUUID().toString())
                .quantity(inventoryRequest.getQuantity())
                .build();
    }
}
