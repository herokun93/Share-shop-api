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
public class CategoryAndSubResponse {
    private Long id;
    private String name;
    private List<SubCategoryResponse> subCategoryResponses;

    public  CategoryAndSubResponse categoryConvert(Category category){
        ModelMapper modelMapper = new ModelMapper();
        subCategoryResponses = new ArrayList<>();

        Collection<SubCategory> subCategories = category.getSubCategories();
        subCategories.forEach(s->{
            SubCategoryResponse subCategoryResponse = new SubCategoryResponse();
            subCategoryResponses.add(subCategoryResponse.subCategoryConvert(s));
        });

        CategoryAndSubResponse categoryAndSubResponse = modelMapper.map(category,CategoryAndSubResponse.class);
        categoryAndSubResponse.setSubCategoryResponses(subCategoryResponses);

        return  categoryAndSubResponse;
    }


}
