package com.qb.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductWithOrderQuantityDTO {
    private ProductDTO productDTO;
    private Integer orderedProductQuantity;
}
