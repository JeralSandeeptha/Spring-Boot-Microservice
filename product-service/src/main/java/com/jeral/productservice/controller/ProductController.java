package com.jeral.productservice.controller;

import com.jeral.productservice.dto.request.ProductRequtesDTO;
import com.jeral.productservice.dto.response.ProductResponseDTO;
import com.jeral.productservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequtesDTO productRequtesDTO) {
        productService.createProduct(productRequtesDTO);
    }

    @GetMapping("/list")
    public List<ProductResponseDTO> getAllProducts() {
        return productService.getAllProducts();
    }
}
