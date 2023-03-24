package com.lengoquyen0411.admin.Service;

import com.lengoquyen0411.admin.Model.Category;

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

//    /*Customer*/
//    List<CategoryDto> getCategoryAndProduct();


}

