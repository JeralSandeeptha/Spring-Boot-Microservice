package com.jeral.inventoryservice.controller;

import com.jeral.inventoryservice.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/inventory")
public class InventoryController {

    @Autowired
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/{sku_code}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isStock(@PathVariable String sku_code) {
        return inventoryService.isInStock(sku_code);
    }
}
