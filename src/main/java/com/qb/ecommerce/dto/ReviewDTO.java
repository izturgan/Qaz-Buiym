package com.qb.ecommerce.dto;

import com.qb.ecommerce.dto.ProductDTO;
import com.qb.ecommerce.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private Long reviewId;
    private UserDTO user;
    private ProductDTO product;
    private Integer rating;
    private String comment;
}