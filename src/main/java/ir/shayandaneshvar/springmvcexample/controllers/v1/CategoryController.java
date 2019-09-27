package ir.shayandaneshvar.springmvcexample.controllers.v1;

import ir.shayandaneshvar.springmvcexample.api.v1.model.CategoryDTO;
import ir.shayandaneshvar.springmvcexample.api.v1.model.CategoryListDTO;
import ir.shayandaneshvar.springmvcexample.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {
    static final String BASE_URL = "/api/v1/categories";

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<CategoryListDTO> getAllCategories() {
        return new ResponseEntity<>(new CategoryListDTO(categoryService.
                getAllCategories()), HttpStatus.OK
        );
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }

    @GetMapping("/{name}")
    public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable String name) {
        return new ResponseEntity<>(categoryService.getCategoryByName(name),
                HttpStatus.OK);
    }
}
