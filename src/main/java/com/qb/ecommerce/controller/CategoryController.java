package com.qb.ecommerce.controller;

import com.qb.ecommerce.dto.CategoryByOrderQuantityDTO;
import com.qb.ecommerce.dto.CategoryDTO;
import com.qb.ecommerce.service.CategoryService;
import com.qb.ecommerce.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final FileUploadService fileUploadService;

    @GetMapping("")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/top-ordered-categories")
    public ResponseEntity<List<CategoryByOrderQuantityDTO>> getTopCategories() {
        return ResponseEntity.ok(categoryService.getTopCategories());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Optional<CategoryDTO>> getCategory(@PathVariable Long categoryId) {
        Optional<CategoryDTO> categoryDTOOptional = categoryService.getCategory(categoryId);
        if (categoryDTOOptional.isPresent()) {
            return ResponseEntity.ok(categoryDTOOptional);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO, @RequestParam("file") MultipartFile file) {
        CategoryDTO createdCategory = categoryService.createCategory(categoryDTO);

        String filePath = fileUploadService.upload(file);
        categoryDTO.setPictureUrl(filePath);

        return ResponseEntity.created(
                        ServletUriComponentsBuilder.fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(createdCategory.getCategoryId())
                                .toUri())
                .body(createdCategory);
    }

    @PutMapping("/update/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryDTO categoryDTO) {
        categoryDTO.setCategoryId(categoryId);
        CategoryDTO updatedCategoryDTO = categoryService.updateCategory(categoryDTO);
        return ResponseEntity.ok(updatedCategoryDTO);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }
}
