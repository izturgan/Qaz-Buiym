package com.qb.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity(name = "payment")
@Data
public class Payment {

    @Id
    @SequenceGenerator(
            name = "payment_sequence",
            sequenceName = "payment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "payment_sequence",
            strategy = GenerationType.SEQUENCE)
    private Long orderId;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate paymentDate;

    @Column(nullable = false)
    private String paymentMethod;

    @Column(nullable = false)
    private Double amount;
}
