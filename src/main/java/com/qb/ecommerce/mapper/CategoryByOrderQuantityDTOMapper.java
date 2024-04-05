package com.qb.ecommerce.mapper;

import com.qb.ecommerce.dto.CategoryByOrderQuantityDTO;
import org.springframework.stereotype.Component;

@Component
public class CategoryByOrderQuantityDTOMapper {
    public CategoryByOrderQuantityDTO map(Object[] row) {
        Long categoryId = (Long) row[0];
        String name = row[1].toString();
        String pictureUrl = row[2].toString();
        Integer orderQuantity = Integer.parseInt(row[3].toString());

        return new CategoryByOrderQuantityDTO(categoryId, name, pictureUrl, orderQuantity);
    }
}
