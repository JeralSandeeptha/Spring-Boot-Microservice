package com.jeral.productservice.service;

import com.jeral.productservice.dto.request.ProductRequtesDTO;
import com.jeral.productservice.dto.response.ProductResponseDTO;
import com.jeral.productservice.model.Product;
import com.jeral.productservice.repo.ProductRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j //this is a logger system in lombok
public class ProductService {

    @Autowired
    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public void createProduct(ProductRequtesDTO productRequtesDTO) {
        Product product = Product.builder()
                    .name(productRequtesDTO.getName())
                    .description(productRequtesDTO.getDescription())
                    .price(productRequtesDTO.getPrice())
                    .build();

        productRepo.save(product);

        log.info("Product " + product.getId() + " is saved");
    }

    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products = productRepo.findAll();
        return products.stream().map(product -> mapToProductResponse(product)).toList();
    }

    private ProductResponseDTO mapToProductResponse(Product product) {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
