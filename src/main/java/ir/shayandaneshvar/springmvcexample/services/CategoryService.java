package ir.shayandaneshvar.springmvcexample.services;

import ir.shayandaneshvar.springmvcexample.api.v1.model.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryByName(String name);
}
