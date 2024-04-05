package com.qb.ecommerce.service;

import com.qb.ecommerce.dto.CategoryByOrderQuantityDTO;
import com.qb.ecommerce.dto.CategoryDTO;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();

    List<CategoryByOrderQuantityDTO> getTopCategories();

    Optional<CategoryDTO> getCategory(Long categoryId);

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO updateCategory(CategoryDTO categoryDTO);

    void deleteCategory(Long categoryId);
}