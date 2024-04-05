package com.qb.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "order_item")
@Data
public class OrderItem {

    @Id
    @SequenceGenerator(
            name = "order_item_sequence",
            sequenceName = "order_item_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "order_item_sequence",
            strategy = GenerationType.SEQUENCE)
    private Long orderItemId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double unitPrice;

    public void setUnitPrice() {
        this.unitPrice = getProduct().getPrice();
    }
}
