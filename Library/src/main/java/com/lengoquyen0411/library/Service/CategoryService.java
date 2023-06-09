package com.lengoquyen0411.library.Service;

import com.lengoquyen0411.library.DTO.CategoryDTO;
import com.lengoquyen0411.library.Model.Category;

import java.util.List;

public interface CategoryService {
    /*Admin*/
    List<Category> findAll();
    Category save(Category category);
    Category findById(Long id);
    Category update(Category category);
    void deleteById(Long id);
    void enabledById(Long id);
    List<Category> findAllByActivated();

    /*Customer*/
    List<CategoryDTO> getCategoryAndProduct();
}

