package share.shop.payloads.response;

import lombok.*;
import org.modelmapper.ModelMapper;
import share.shop.models.SubCategory;
import share.shop.payloads.audit.DateAuditResponse;

@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoryResponse  {
    private String name;
    private Long id;
    private String slug;
    private boolean enable;

    public SubCategoryResponse subCategoryConvert(SubCategory subCategory){
        ModelMapper modelMapper = new ModelMapper();
        SubCategoryResponse subCategoryResponse = modelMapper.map(subCategory, SubCategoryResponse.class);
        return  subCategoryResponse;
    }
}
