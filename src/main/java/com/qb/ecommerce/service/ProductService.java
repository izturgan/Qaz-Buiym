package com.qb.ecommerce.service;

import com.qb.ecommerce.dto.ProductByRatingDTO;
import com.qb.ecommerce.dto.ProductDTO;
import com.qb.ecommerce.dto.ProductWithOrderQuantityDTO;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductDTO> getAllProducts();

    List<ProductByRatingDTO> getTopRatedProducts();

    List<ProductWithOrderQuantityDTO> getBestSellerProducts();

    List<ProductDTO> getProductsByFilter(
            List<Long> categoryIds,
            Integer minPrice,
            Integer maxPrice,
            Boolean isOrderByPriceDesc
    );

    Optional<ProductDTO> getProduct(Long productId);

    ProductDTO createProduct(ProductDTO productDTO);

    ProductDTO updateProduct(ProductDTO productDTO);

    void deleteProduct(Long productId);
}