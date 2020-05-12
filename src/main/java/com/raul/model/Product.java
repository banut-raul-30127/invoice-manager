package com.raul.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "product")
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Product {

    @Id
    private String id;
    private int productNumber;
    private String name;
    private double price;
}
