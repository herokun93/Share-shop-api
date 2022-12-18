package share.shop.payloads.response;


import lombok.*;
import org.modelmapper.ModelMapper;
import share.shop.models.Category;
import share.shop.models.SubCategory;

import java.util.*;

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

        subCategories.stream().sorted(new Comparator<SubCategory>() {
            @Override
            public int compare(SubCategory o1, SubCategory o2) {
                return o2.getId().compareTo(o1.getId());
            }
        });


        subCategories.forEach(s->{
            SubCategoryResponse subCategoryResponse = new SubCategoryResponse().subCategoryConvert(s);
            if(subCategoryResponse.isEnable()){
                children.add(subCategoryResponse);
            }

        });



        CategoryResponse categoryResponse = modelMapper.map(category,CategoryResponse.class);
        categoryResponse.setChildren(children);
        return categoryResponse;
    }


}
