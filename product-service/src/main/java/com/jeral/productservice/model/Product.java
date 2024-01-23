package com.jeral.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "product")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

    @Id
    private String id;
    private String name;
    private String description;
    private String price;

}
