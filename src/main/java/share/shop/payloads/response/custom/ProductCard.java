package share.shop.payloads.response.custom;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import share.shop.payloads.response.icustom.ITProduct;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public  class ProductCard  {

    private Long productId;
    private String productName;
    private boolean productHot;
    private int productRating;
    private String imageUrlSmall;
    private Long imageId;
    private List<OTag> tags;


    public ProductCard(ITProduct ITProduct) {
        this.productId = ITProduct.getProductId();
        this.productName = ITProduct.getProductName();
        this.productHot = ITProduct.getProductHot();
        this.productRating = ITProduct.getProductRating();
    }
    public ProductCard(List<ITProduct> ITProducts) {
        if(ITProducts.size()>0){
            this.productId = ITProducts.get(0).getProductId();
            this.productName = ITProducts.get(0).getProductName();
            this.productHot = ITProducts.get(0).getProductHot();
            this.productRating = ITProducts.get(0).getProductRating();
            this.imageId = ITProducts.get(0).getImageId();
            this.imageUrlSmall = ITProducts.get(0).getImageUrlSmall();

            this.tags = new ArrayList<>();
            ITProducts.forEach(p->{
                tags.add(new OTag(p.getTagId(),p.getTagName()));
            });
        }
    };

}
