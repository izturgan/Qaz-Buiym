package com.qb.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryByOrderQuantityDTO {
    private Long categoryId;
    private String name;
    private String pictureUrl;
    private Integer orderQuantity;
}
