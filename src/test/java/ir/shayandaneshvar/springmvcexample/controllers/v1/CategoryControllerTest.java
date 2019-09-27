package ir.shayandaneshvar.springmvcexample.controllers.v1;

import ir.shayandaneshvar.springmvcexample.api.RestResponseExceptionHandler;
import ir.shayandaneshvar.springmvcexample.api.v1.model.CategoryDTO;
import ir.shayandaneshvar.springmvcexample.exceptions.ResourceNotFoundException;
import ir.shayandaneshvar.springmvcexample.services.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CategoryControllerTest {

    public static final String NAME = "Jack";

    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc =
                MockMvcBuilders.standaloneSetup(categoryController).setControllerAdvice(
                        new RestResponseExceptionHandler()).build();
    }

    @Test
    public void testListCategories() throws Exception {
        CategoryDTO categoryDTO1 = new CategoryDTO().setId(1l).setName(NAME);
        CategoryDTO categoryDTO2 = new CategoryDTO().setId(2l).setName("Bill");

        List<CategoryDTO> categoryDTOS = Arrays.asList(categoryDTO1, categoryDTO2);

        when(categoryService.getAllCategories()).thenReturn(categoryDTOS);

        mockMvc.perform(get(CategoryController.getBaseUrl()).contentType(MediaType.
                APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath(
                "$.categories", hasSize(2)));
    }

    @Test
    public void testGetByNameCategories() throws Exception {
        CategoryDTO categoryDTO1 = new CategoryDTO().setId(1l).setName(NAME);
        when(categoryService.getCategoryByName(anyString())).thenReturn(categoryDTO1);
        mockMvc.perform(get(CategoryController.getBaseUrl() + "/" + NAME).
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)));

    }

    @Test
    public void testGetByNameNotFound() throws Exception {
        when(categoryService.getCategoryByName(anyString()))
                .thenThrow(ResourceNotFoundException.class);
        mockMvc.perform(get(CategoryController.getBaseUrl() + "/Foo").
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }
}