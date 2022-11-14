package share.shop.payloads;


import lombok.*;
import org.modelmapper.ModelMapper;
import share.shop.models.Category;

@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {

    private Long id;
    private String name;

    public CategoryResponse categoryResponseConvert(Category category){
        ModelMapper modelMapper = new ModelMapper();
        CategoryResponse categoryResponse = modelMapper.map(category, CategoryResponse.class);
        return  categoryResponse;
    }
}
