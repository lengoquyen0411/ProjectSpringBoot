package com.lengoquyen0411.library.DTO;

import com.lengoquyen0411.library.Model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private double costPrice;
    private double salePrice;
    private int currentQuantity;
    private Category category;
    private String image;
    private boolean activated;
    private boolean deleted;
}
