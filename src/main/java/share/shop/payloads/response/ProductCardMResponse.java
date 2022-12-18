package share.shop.payloads.response;

import lombok.*;
import org.modelmapper.ModelMapper;
import share.shop.models.Image;
import share.shop.models.Product;
import share.shop.models.Tag;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCardMResponse {
    private Long id;
    private String name;
    private boolean hot;
    private int rating;
    private String descriptionSort;
    private String tiktok;
    private boolean enable;
    private String countryName;
    private Long countryId;
    private Long subCategoryId;
    private String subCategoryName;
    private int option;
    private int mode;
    private String slug;
    private List<ImageMResponse> imageMResponses;
    private List<TagResponse> tagResponseList;

    public ProductCardMResponse productCardConvert(Product product){
        ModelMapper modelMapper = new ModelMapper();
        imageMResponses = new ArrayList<>();

        Collection<Image> images = product.getImages();
        images.forEach(e->{
            ImageMResponse imageMResponse = new ImageMResponse();
            if(e.getPriority()==1){
                imageMResponses.add(imageMResponse.imageResponseConvert(e));
            }
        });

        tagResponseList = new ArrayList<>();
        Collection<Tag> tags = product.getTags();
        tags.forEach(t->{
            TagResponse tagResponse = new TagResponse();
            tagResponseList.add(tagResponse.tagConvert(t));
        });


        ProductCardMResponse productCardMResponse = modelMapper.map(product, ProductCardMResponse.class);
        productCardMResponse.setImageMResponses(imageMResponses);
        productCardMResponse.setTagResponseList(tagResponseList);

        return productCardMResponse;
    }
}
