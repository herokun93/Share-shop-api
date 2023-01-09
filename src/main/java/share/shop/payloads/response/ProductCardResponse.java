package share.shop.payloads.response;

import lombok.*;
import org.modelmapper.ModelMapper;
import share.shop.models.Image;
import share.shop.models.Price;
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
    private int mode;
    private boolean sale;
    private long price;
    private long salePrice;
    private String slug;
    private LocalDateTime until;
    private List<ImageResponse> imageResponseList;
    private List<TagResponse> tagResponseList;
    private List<PriceResponse> priceResponses;

    public ProductCardResponse productCardConvert(Product product){
        ModelMapper modelMapper = new ModelMapper();
        imageResponseList = new ArrayList<>();
        priceResponses = new ArrayList<>();
        tagResponseList = new ArrayList<>();

        Collection<Image> images = product.getImages();

        if(images.size()>=1){
            ImageResponse imageResponse = new ImageResponse();
            imageResponseList.add(imageResponse.imageResponseConvert(images.stream().findFirst().get()));
        }



        Collection<Tag> tags = product.getTags();
        tags.forEach(t->{
            TagResponse tagResponse = new TagResponse();
            tagResponseList.add(tagResponse.tagConvert(t));
        });

        Collection<Price> prices = product.getPrices();
        prices.forEach(p->{
            priceResponses.add(new PriceResponse().priceResponseConvert(p));
        });


        ProductCardResponse productCardResponse = modelMapper.map(product, ProductCardResponse.class);
        productCardResponse.setImageResponseList(imageResponseList);
        productCardResponse.setTagResponseList(tagResponseList);
        productCardResponse.setPriceResponses(priceResponses);

        return productCardResponse;
    }
}
