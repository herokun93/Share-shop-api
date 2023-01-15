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
    private Collection<ImageResponse> images;
    private Collection<TagResponse> tags;
    private List<PriceResponse> prices;

    public ProductCardResponse productCardConvert(Product product){
        ModelMapper modelMapper = new ModelMapper();
        images = new ArrayList<>();
        prices = new ArrayList<>();
        tags = new ArrayList<>();

       Collection<Image> entityImage = product.getImages();


        if(entityImage.size()>=1){
            images.add(new ImageResponse().imageResponseConvert(entityImage.stream().findFirst().get()));
        }



        Collection<Tag> entityTags = product.getTags();
        entityTags.forEach(t->{
            TagResponse tagResponse = new TagResponse();
            tags.add(tagResponse.tagConvert(t));
        });

        Collection<Price> entityPrices = product.getPrices();
        entityPrices.forEach(p->{
            prices.add(new PriceResponse().priceResponseConvert(p));
        });


        ProductCardResponse productCardResponse = modelMapper.map(product, ProductCardResponse.class);
        productCardResponse.setImages(images);
        productCardResponse.setTags(tags);
        productCardResponse.setPrices(prices);

        return productCardResponse;
    }
}
