package share.shop.payloads;

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
public class ProductCard {
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
    private int featured;
    private List<ImageResponse> imageResponseList;
    private List<TagResponse> tagResponseList;

    public ProductCard productCardConvert(Product product){
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


        ProductCard productCard = modelMapper.map(product, ProductCard.class);
        productCard.setImageResponseList(imageResponseList);
        productCard.setTagResponseList(tagResponseList);

        return productCard;
    }
}
