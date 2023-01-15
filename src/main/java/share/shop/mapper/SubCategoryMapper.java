package share.shop.mapper;

import org.modelmapper.ModelMapper;
import share.shop.dto.SubCategoryViewDto;
import share.shop.models.SubCategory;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryMapper {
    private static SubCategoryViewDto convert(SubCategory subCategory){
        ModelMapper modelMapper = new ModelMapper();

        SubCategoryViewDto subCategoryViewDto= modelMapper.map(subCategory,SubCategoryViewDto.class);
        return  subCategoryViewDto;
    }

    public static List<SubCategoryViewDto> listConverts(List<SubCategory> subCategories){

        List<SubCategoryViewDto> subCategoryViewDtos = new ArrayList<>();
        subCategories.forEach(subCategory -> {
            subCategoryViewDtos.add(convert(subCategory));
        });
        return  subCategoryViewDtos;

    }

}
