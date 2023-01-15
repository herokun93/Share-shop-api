package share.shop.mapper;

import org.modelmapper.ModelMapper;
import share.shop.dto.CategoryViewDto;
import share.shop.models.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryMapper {
    public static CategoryViewDto convert(Category category){

        ModelMapper modelMapper = new ModelMapper();

        CategoryViewDto categoryViewDto = modelMapper.map(category,CategoryViewDto.class);
        return  categoryViewDto;
    }
    public static List<CategoryViewDto> listCategories(List<Category> categories){

        List<CategoryViewDto> categoryViewDtos = new ArrayList<>();

        categories.forEach(category -> {
            categoryViewDtos.add(convert(category));
        });
        return  categoryViewDtos;
    }
}
