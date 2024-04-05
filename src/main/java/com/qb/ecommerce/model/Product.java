package com.qb.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "product")
@Data
public class Product {

    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "product_sequence",
            strategy = GenerationType.SEQUENCE)
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column
    private Double newPrice;

    @Column(nullable = false)
    private Integer quantityAvailable;

    @Column(nullable = false)
    private String mainPictureUrl;

    @Column(nullable = false)
    private String secondaryPictureUrl;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Boolean isNew;
}
