package com.qb.ecommerce.repository;

import com.qb.ecommerce.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT " +
            "   r.product.productId, " +
            "   r.product.name, " +
            "   r.product.price, " +
            "   r.product.newPrice, " +
            "   r.product.quantityAvailable, " +
            "   r.product.mainPictureUrl, " +
            "   r.product.secondaryPictureUrl, " +
            "   r.product.description, " +
            "   r.product.isNew, " +
            "   r.product.category.categoryId, " +
            "   r.product.category.name AS categoryName, " +
            "   r.product.category.pictureUrl, " +
            "   AVG(r.rating) AS productRating " +
            "FROM review r " +
            "GROUP BY r.product.productId, " +
            "   r.product.name, " +
            "   r.product.price, " +
            "   r.product.newPrice, " +
            "   r.product.quantityAvailable, " +
            "   r.product.mainPictureUrl, " +
            "   r.product.secondaryPictureUrl, " +
            "   r.product.description, " +
            "   r.product.isNew, " +
            "   r.product.category.categoryId, " +
            "   r.product.category.name, " +
            "   r.product.category.pictureUrl " +
            "ORDER BY productRating DESC " +
            "LIMIT 4")
    List<Object[]> getAverageRatingByProduct();
}
