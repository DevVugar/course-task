package az.ingress.mapper;


import az.ingress.model.dto.requset.CategoryRequestDto;
import az.ingress.model.dto.response.CategoryResponseDto;
import az.ingress.model.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    //CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category toEntity(CategoryRequestDto dto);
    CategoryResponseDto toDto(Category category);
}

