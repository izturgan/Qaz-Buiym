package com.qb.ecommerce.controller;

import com.qb.ecommerce.dto.ProductByRatingDTO;
import com.qb.ecommerce.dto.ProductDTO;
import com.qb.ecommerce.dto.ProductWithOrderQuantityDTO;
import com.qb.ecommerce.service.FileUploadService;
import com.qb.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final FileUploadService fileUploadService;

    @GetMapping("")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/top-rated-products")
    public ResponseEntity<List<ProductByRatingDTO>> getTopRatedProducts() {
        return ResponseEntity.ok(productService.getTopRatedProducts());
    }

    @GetMapping("/best-seller-products")
    public ResponseEntity<List<ProductWithOrderQuantityDTO>> getBestSellerProducts() {
        return ResponseEntity.ok(productService.getBestSellerProducts());
    }

    @GetMapping("/by-filter")
    public ResponseEntity<List<ProductDTO>> getProductsByFilter(
            @RequestParam(required = false, name = "categoryIds") List<Long> categoryIds,
            @RequestParam(required = false, name = "minPrice") Integer minPrice,
            @RequestParam(required = false, name = "maxPrice") Integer maxPrice,
            @RequestParam(required = false, name = "isOrderByPriceDesc") Boolean isOrderByPriceDesc
    ) {
        return ResponseEntity.ok(productService.getProductsByFilter(
                categoryIds,
                minPrice,
                maxPrice,
                isOrderByPriceDesc
        ));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Optional<ProductDTO>> getProduct(@PathVariable Long productId) {
        Optional<ProductDTO> productDTOOptional = productService.getProduct(productId);
        if (productDTOOptional.isPresent()) {
            return ResponseEntity.ok(productDTOOptional);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ProductDTO> createProduct(
            @RequestBody ProductDTO productDTO,
            @RequestParam("mainFile") MultipartFile mainFile,
            @RequestParam("secondaryFile") MultipartFile secondaryFile
    ) {
        ProductDTO createdProduct = productService.createProduct(productDTO);

        String filePath = fileUploadService.upload(mainFile);
        productDTO.setMainPictureUrl(filePath);

        filePath = fileUploadService.upload(secondaryFile);
        productDTO.setSecondaryPictureUrl(filePath);

        return ResponseEntity.created(
                        ServletUriComponentsBuilder.fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(createdProduct.getProductId())
                                .toUri())
                .body(createdProduct);
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(
            @PathVariable Long productId,
            @RequestBody ProductDTO productDTO
    ) {
        productDTO.setProductId(productId);
        ProductDTO updatedProductDTO = productService.updateProduct(productDTO);
        return ResponseEntity.ok(updatedProductDTO);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}
