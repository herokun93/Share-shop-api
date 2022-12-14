package share.shop.payloads.response;


import lombok.*;
import org.modelmapper.ModelMapper;
import share.shop.models.Category;
import share.shop.models.SubCategory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {

    private Long id;
    private String name;
    private String search;
    private List<SubCategoryResponse> children;

    public CategoryResponse categoryResponseConvert(Category category){
        ModelMapper modelMapper = new ModelMapper();
        CategoryResponse categoryResponse = modelMapper.map(category, CategoryResponse.class);
        return  categoryResponse;
    }
    public CategoryResponse getAllSubCategoryOrAllCategory(Category category){
        ModelMapper modelMapper = new ModelMapper();
        children = new ArrayList<>();

        Collection<SubCategory> subCategories = category.getSubCategories();
        subCategories.forEach(s->{
            SubCategoryResponse subCategoryResponse = new SubCategoryResponse();
            children.add(subCategoryResponse.subCategoryConvert(s));
        });

        CategoryResponse categoryResponse = modelMapper.map(category,CategoryResponse.class);
        categoryResponse.setChildren(children);
        return categoryResponse;
    }


}
