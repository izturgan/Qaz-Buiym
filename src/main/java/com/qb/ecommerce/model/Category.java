package com.qb.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "category")
@Data
public class Category {

    @Id
    @SequenceGenerator(
            name = "category_sequence",
            sequenceName = "category_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "category_sequence",
            strategy = GenerationType.SEQUENCE)
    private Long categoryId;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String pictureUrl;
}
