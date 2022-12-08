package share.shop.payloads;

import lombok.*;
import org.modelmapper.ModelMapper;
import share.shop.models.Image;
import share.shop.models.Product;
import share.shop.models.Tag;
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
public class ProductDetails extends UserDateAuditResponse {
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
    private int option;
    private List<ImageResponse> imageResponseList;
    private List<TagResponse> tagResponseList;

    public ProductDetails productDetailsConvert(Product product){
        ModelMapper modelMapper = new ModelMapper();
        imageResponseList = new ArrayList<>();

        Collection<Image> images = product.getImages();
        images.forEach(e->{
            ImageResponse imageResponse = new ImageResponse();
            imageResponseList.add(imageResponse.imageResponseConvert(e));
        });

        tagResponseList = new ArrayList<>();
        Collection<Tag> tags = product.getTags();
        tags.forEach(t->{
            TagResponse tagResponse = new TagResponse();
            tagResponseList.add(tagResponse.tagConvert(t));
        });


        ProductDetails productDetails = modelMapper.map(product, ProductDetails.class);
        productDetails.setImageResponseList(imageResponseList);
        productDetails.setTagResponseList(tagResponseList);

        return productDetails;
    }
}
