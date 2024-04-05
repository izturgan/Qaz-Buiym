package com.qb.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "cart_item")
@Data
public class CartItem {

    @Id
    @SequenceGenerator(
            name = "cart_item_sequence",
            sequenceName = "cart_item_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "cart_item_sequence",
            strategy = GenerationType.SEQUENCE)
    private Long cartItemId;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private Double quantity;
}
