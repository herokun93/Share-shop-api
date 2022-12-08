package share.shop.payloads;

import lombok.*;
import org.modelmapper.ModelMapper;
import share.shop.models.Country;
import share.shop.models.SubCategory;
import share.shop.payloads.audit.DateAuditResponse;

@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoryResponse extends DateAuditResponse {
    private String name;
    private Long id;
    private String search;

    public SubCategoryResponse subCategoryConvert(SubCategory subCategory){
        ModelMapper modelMapper = new ModelMapper();
        SubCategoryResponse subCategoryResponse = modelMapper.map(subCategory, SubCategoryResponse.class);
        return  subCategoryResponse;
    }
}
