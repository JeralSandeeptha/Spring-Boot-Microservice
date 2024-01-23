package com.jeral.inventoryservice;

import com.jeral.inventoryservice.model.Inventory;
import com.jeral.inventoryservice.repo.InventoryRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepo inventoryRepo) {
		return args -> {
			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("item_1");
			inventory1.setQuantity(25);

			Inventory inventory2 = new Inventory();
			inventory2.setSkuCode("item_2");
			inventory2.setQuantity(50);

			inventoryRepo.save(inventory1);
			inventoryRepo.save(inventory2);
		};
	}

}