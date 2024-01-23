package com.jeral.inventoryservice.Service;

import com.jeral.inventoryservice.repo.InventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryService {

    @Autowired
    private final InventoryRepo inventoryRepo;

    public InventoryService(InventoryRepo inventoryRepo) {
        this.inventoryRepo = inventoryRepo;
    }

    @Transactional(readOnly = true)
    public boolean isInStock(String sku_code) {
        return inventoryRepo.findBySkuCode(sku_code).isPresent();
    }
}
