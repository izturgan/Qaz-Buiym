package com.qb.ecommerce.util;

import com.qb.ecommerce.dto.ProductDTO;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductSortingUtil {
    public static List<ProductDTO> sortProducts(
            List<ProductDTO> allProducts,
            List<Long> categoryIds,
            Integer minPrice,
            Integer maxPrice,
            Boolean isOrderByPriceDesc
    ) {

        List<ProductDTO> filteredProducts = allProducts.stream()
                .filter(product -> categoryIds == null || categoryIds.isEmpty() || categoryIds.contains(product.getCategory().getCategoryId()))
                .filter(product -> minPrice == null || product.getPrice() >= minPrice)
                .filter(product -> maxPrice == null || product.getPrice() <= maxPrice)
                .collect(Collectors.toList());

        if (isOrderByPriceDesc != null && isOrderByPriceDesc) {
            filteredProducts.sort(Comparator.comparing(ProductDTO::getPrice).reversed());
        } else if (isOrderByPriceDesc != null) {
            filteredProducts.sort(Comparator.comparing(ProductDTO::getPrice));
        }

        return filteredProducts;
    }
}
