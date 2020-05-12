package com.raul.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "company")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Company {

    @Id
    private String id;

    @TextIndexed
    private String name;

    private String phoneNumber;
}

