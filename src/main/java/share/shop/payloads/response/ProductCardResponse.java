package share.shop.payloads.response;

import lombok.*;
import org.modelmapper.ModelMapper;
import share.shop.models.Image;
import share.shop.models.Product;
import share.shop.models.Tag;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCardResponse {
    private Long id;
    private String name;
    private boolean hot;
    private int rating;
    private String descriptionSort;
    private String countryName;
    private String subCategoryName;
    private Long subCategoryId;
    private int option;
    private int featured;
    private boolean sale;
    private long price;
    private long sale_price;
    private LocalDateTime until;
    private List<ImageResponse> imageResponseList;
    private List<TagResponse> tagResponseList;

    public ProductCardResponse productCardConvert(Product product){
        ModelMapper modelMapper = new ModelMapper();
        imageResponseList = new ArrayList<>();

        Collection<Image> images = product.getImages();
        images.forEach(e->{
            ImageResponse imageResponse = new ImageResponse();
            if(e.getPriority()==1){
                imageResponseList.add(imageResponse.imageResponseConvert(e));
            }
        });

        tagResponseList = new ArrayList<>();
        Collection<Tag> tags = product.getTags();
        tags.forEach(t->{
            TagResponse tagResponse = new TagResponse();
            tagResponseList.add(tagResponse.tagConvert(t));
        });


        ProductCardResponse productCardResponse = modelMapper.map(product, ProductCardResponse.class);
        productCardResponse.setImageResponseList(imageResponseList);
        productCardResponse.setTagResponseList(tagResponseList);

        return productCardResponse;
    }
}
