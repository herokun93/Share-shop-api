package share.shop.payloads.response.custom;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import share.shop.payloads.response.custom.OTag;
import share.shop.payloads.response.icustom.ITProduct;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@ToString
public class ProductDetails {
    private Long productId;
    private String productName;
    private boolean productHot;
    private int productRating;
    private String description;
    private String productDescriptionSort;
    private String productTiktok;
    private LocalDateTime productUntil;
    private OPrice price;
    private HashSet<OImage> images;
    private HashSet<OTag> tags;
    public ProductDetails(List<ITProduct> itProducts) {
        if(itProducts.size()>0){
            ITProduct itProduct = itProducts.get(0);
            this.productId = itProduct.getProductId();
            this.productName = itProduct.getProductName();
            this.productHot = itProduct.getProductHot();
            this.productRating = itProduct.getProductRating();
            this.productHot = itProduct.getProductHot();
            this.description = itProduct.getProductDescription();
            this.productDescriptionSort = itProduct.getProductDescriptionSort();
            this.productTiktok = itProduct.getProductTiktok();
            this.productUntil = itProduct.getProductUntil();
            this.price = new OPrice();

            this.price.setPriceId(itProduct.getPriceId());
            this.price.setPriceName(itProduct.getPriceName());
            this.price.setPricePrice(itProduct.getPricePrice());
            this.price.setPriceSalePrice(itProduct.getPriceSalePrice());
            this.price.setPriceStartAt(itProduct.getPriceStartAt());
            this.price.setPriceFinishAt(itProduct.getPriceFinishAt());
            this.price.setPriceSale(itProduct.getPriceSale());

            this.images = new HashSet<OImage>();
            this.tags = new HashSet<OTag>();

            itProducts.forEach(p->{
                OTag oTag = new OTag(p.getTagId(),p.getTagName());
                if(!tags.contains(oTag)) tags.add(oTag);

                OImage oImage = new OImage(p.getImageId(),p.getImageUrlSmall());

                if(!images.contains(oImage)) images.add(oImage);
            });
        }
    };
}
