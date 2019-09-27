package ir.shayandaneshvar.springmvcexample.controllers.v1;

import ir.shayandaneshvar.springmvcexample.api.v1.model.CategoryDTO;
import ir.shayandaneshvar.springmvcexample.api.v1.model.CategoryListDTO;
import ir.shayandaneshvar.springmvcexample.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {
    static final String BASE_URL = "/api/v1/categories";

    private final CategoryService categoryService;

    public static String getBaseUrl() {
        return BASE_URL;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDTO getAllCategories() {
        return new CategoryListDTO(categoryService.getAllCategories());
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategoryByName(@PathVariable String name) {
        return categoryService.getCategoryByName(name);
    }
}
