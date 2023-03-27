package com.lengoquyen0411.library.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class CategoryDTO {
    private Long categoryId;
    private String categoryName;
    private Long numberOfProduct;
}
