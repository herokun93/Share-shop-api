package share.shop.payloads;

import lombok.*;
import org.modelmapper.ModelMapper;
import share.shop.models.Image;
import share.shop.models.Product;
import share.shop.payloads.audit.UserDateAuditResponse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInformation extends UserDateAuditResponse {
    private Long id;
    private String name;
    private boolean hot;
    private int rating;
    private String description;
    private String descriptionSort;
    private String tiktok;
    private boolean enable;
    private Long countryId;
    private Long subCategoryId;
    private List<ImageResponse> imageResponseList;

    public ProductInformation productInformationConvert(Product product){
        ModelMapper modelMapper = new ModelMapper();
        List<ImageResponse> imageResponseList = new ArrayList<>();

        Collection<Image> images = product.getImages();
        images.forEach(e->{
            ImageResponse imageResponse = new ImageResponse();
            imageResponseList.add(imageResponse.imageResponseConvert(e));
        });


        ProductInformation productInformation = modelMapper.map(product,ProductInformation.class);
        productInformation.setImageResponseList(imageResponseList);

        return  productInformation;
    }
}
