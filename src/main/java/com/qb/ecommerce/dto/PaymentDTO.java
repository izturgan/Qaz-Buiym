package com.qb.ecommerce.dto;

import com.qb.ecommerce.dto.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private Long paymentId;
    private OrderDTO order;
    private LocalDate paymentDate;
    private String paymentMethod;
    private Double amount;
}