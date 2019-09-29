package ir.shayandaneshvar.springmvcexample.api.v1.mapper;

import ir.shayandaneshvar.springmvcexample.api.v1.model.CategoryDTO;
import ir.shayandaneshvar.springmvcexample.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(source = "id", target = "id")
    CategoryDTO categoryToCategoryDTO(Category category);
}
