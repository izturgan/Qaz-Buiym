package com.qb.ecommerce.mapper;

import com.qb.ecommerce.dto.ProductDTO;
import com.qb.ecommerce.dto.ProductWithOrderQuantityDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductByOrderQuantityDTOMapper {
    public ProductWithOrderQuantityDTO map(Object[] row) {
        ProductDTO productDTO = ProductDTO.getProductDTO(row);
        Integer orderedProductQuantity = Integer.parseInt(row[12].toString());

        return new ProductWithOrderQuantityDTO(productDTO, orderedProductQuantity);
    }
}
