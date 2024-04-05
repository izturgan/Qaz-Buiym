package com.qb.ecommerce.mapper;

import com.qb.ecommerce.dto.ProductByRatingDTO;
import com.qb.ecommerce.dto.ProductDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductByRatingDTOMapper {

    public ProductByRatingDTO map(Object[] row) {
        ProductDTO productDTO = ProductDTO.getProductDTO(row);
        Double productRating = (Double) row[12];

        return new ProductByRatingDTO(productDTO, productRating);
    }
}
