package com.qb.ecommerce.repository;

import com.qb.ecommerce.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("SELECT " +
            "   oi.product.productId, " +
            "   oi.product.name, " +
            "   oi.product.price, " +
            "   oi.product.newPrice, " +
            "   oi.product.quantityAvailable, " +
            "   oi.product.mainPictureUrl, " +
            "   oi.product.secondaryPictureUrl, " +
            "   oi.product.description, " +
            "   oi.product.isNew, " +
            "   oi.product.category.categoryId, " +
            "   oi.product.category.name AS categoryName, " +
            "   oi.product.category.pictureUrl, " +
            "   SUM(oi.quantity) AS orderedProductQuantity " +
            "FROM order_item oi " +
            "GROUP BY oi.product.productId, " +
            "   oi.product.name, " +
            "   oi.product.price, " +
            "   oi.product.newPrice, " +
            "   oi.product.quantityAvailable, " +
            "   oi.product.mainPictureUrl, " +
            "   oi.product.secondaryPictureUrl, " +
            "   oi.product.description, " +
            "   oi.product.isNew, " +
            "   oi.product.category.categoryId, " +
            "   oi.product.category.name, " +
            "   oi.product.category.pictureUrl " +
            "ORDER BY orderedProductQuantity DESC " +
            "LIMIT 4")
    List<Object[]> getSumQuantityByOrder();
}