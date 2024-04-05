package com.qb.ecommerce.repository;

import com.qb.ecommerce.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT " +
            "   c.categoryId," +
            "   c.name," +
            "   c.pictureUrl," +
            "   SUM(oi.quantity) AS orderQuantity " +
            "FROM category c " +
            "JOIN product p ON p.category.categoryId = c.categoryId " +
            "JOIN order_item oi ON oi.product.productId = p.productId " +
            "GROUP BY c.categoryId, c.name, c.pictureUrl " +
            "ORDER BY orderQuantity DESC " +
            "LIMIT 4")
    List<Object[]> getCategoriesByOrderQuantity();
}
