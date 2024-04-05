package com.qb.ecommerce.service.impl;

import com.qb.ecommerce.config.MapperConfiguration;
import com.qb.ecommerce.dto.ProductByRatingDTO;
import com.qb.ecommerce.dto.ProductDTO;
import com.qb.ecommerce.dto.ProductWithOrderQuantityDTO;
import com.qb.ecommerce.exception.ResourceNotFoundException;
import com.qb.ecommerce.mapper.ProductByOrderQuantityDTOMapper;
import com.qb.ecommerce.mapper.ProductByRatingDTOMapper;
import com.qb.ecommerce.model.Product;
import com.qb.ecommerce.repository.OrderItemRepository;
import com.qb.ecommerce.repository.ProductRepository;
import com.qb.ecommerce.repository.ReviewRepository;
import com.qb.ecommerce.service.ProductService;
import com.qb.ecommerce.util.ProductSortingUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {


    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    private final MapperConfiguration mapper;
    private final ProductByRatingDTOMapper productByRatingDTOMapper;
    private final ProductByOrderQuantityDTOMapper productByOrderQuantityDTOMapper;

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> mapper.getMapper().map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductByRatingDTO> getTopRatedProducts() {
        return reviewRepository.getAverageRatingByProduct()
                .stream()
                .map(productByRatingDTOMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductWithOrderQuantityDTO> getBestSellerProducts() {
        return orderItemRepository.getSumQuantityByOrder()
                .stream()
                .map(productByOrderQuantityDTOMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getProductsByFilter(
            List<Long> categoryIds,
            Integer minPrice,
            Integer maxPrice,
            Boolean isOrderByPriceDesc
    ) {
        List<ProductDTO> allProducts = productRepository.findAll()
                .stream()
                .map(product -> mapper.getMapper().map(product, ProductDTO.class))
                .toList();
        return ProductSortingUtil.sortProducts(
                allProducts,
                categoryIds,
                minPrice,
                maxPrice,
                isOrderByPriceDesc
        );
    }

    @Override
    public Optional<ProductDTO> getProduct(Long productId) {
        return productRepository.findById(productId)
                .map(product -> mapper.getMapper().map(product, ProductDTO.class));
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = mapper.getMapper().map(productDTO, Product.class);
        product = productRepository.save(product);
        return mapper.getMapper().map(product, ProductDTO.class);
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(productDTO.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        mapper.getMapper().map(productDTO, existingProduct);
        return mapper.getMapper().map(existingProduct, ProductDTO.class);
    }

    @Override
    public void deleteProduct(Long productId) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        productRepository.delete(existingProduct);
    }
}
