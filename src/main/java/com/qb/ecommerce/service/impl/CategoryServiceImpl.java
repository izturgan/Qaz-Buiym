package com.qb.ecommerce.service.impl;

import com.qb.ecommerce.config.MapperConfiguration;
import com.qb.ecommerce.dto.CategoryByOrderQuantityDTO;
import com.qb.ecommerce.dto.CategoryDTO;
import com.qb.ecommerce.exception.ResourceNotFoundException;
import com.qb.ecommerce.mapper.CategoryByOrderQuantityDTOMapper;
import com.qb.ecommerce.model.Category;
import com.qb.ecommerce.repository.CategoryRepository;
import com.qb.ecommerce.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final MapperConfiguration mapper;

    private final CategoryByOrderQuantityDTOMapper categoryByOrderQuantityDTOMapper;

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> mapper.getMapper().map(category, CategoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryByOrderQuantityDTO> getTopCategories() {
        return categoryRepository.getCategoriesByOrderQuantity()
                .stream()
                .map(categoryByOrderQuantityDTOMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CategoryDTO> getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .map(category -> mapper.getMapper().map(category, CategoryDTO.class));
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = mapper.getMapper().map(categoryDTO, Category.class);
        category = categoryRepository.save(category);
        return mapper.getMapper().map(category, CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
        Category existingCategory = categoryRepository.findById(categoryDTO.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        mapper.getMapper().map(categoryDTO, existingCategory);
        existingCategory = categoryRepository.save(existingCategory);
        return mapper.getMapper().map(existingCategory, CategoryDTO.class);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        categoryRepository.delete(existingCategory);
    }
}