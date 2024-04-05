package com.qb.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long productId;
    private String name;
    private Double price;
    private Double newPrice;
    private Integer quantityAvailable;
    private String mainPictureUrl;
    private String secondaryPictureUrl;
    private String description;
    private CategoryDTO category;
    private Boolean isNew;

    public static ProductDTO getProductDTO(Object[] row) {
        return new ProductDTO(
                (Long) row[0], // productId
                row[1].toString(), // name
                (Double) row[2], // price
                (Double) row[3], // newPrice
                Integer.parseInt(row[4].toString()), // quantityAvailable
                row[5].toString(), // mainPictureUrl
                row[6].toString(), // secondaryPictureUrl
                row[7].toString(), // description
                new CategoryDTO(
                        (Long) row[9], // categoryId
                        row[10].toString(), // categoryName
                        row[11].toString() // pictureUrl
                ),
                Boolean.valueOf(row[8].toString()) // isNew
        );
    }
}