package com.qb.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity(name = "cart")
@Data
public class Cart {

    @Id
    @SequenceGenerator(
            name = "cart_sequence",
            sequenceName = "cart_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "cart_sequence",
            strategy = GenerationType.SEQUENCE)
    private Long cartId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate createdDate;
}
