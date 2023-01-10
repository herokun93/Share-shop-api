package share.shop.payloads.response.custom;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import share.shop.payloads.response.icustom.ITProduct;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@ToString
@Slf4j
public  class ProductCard  {

    private Long productId;
    private String productName;
    private boolean productHot;
    private int productRating;
    private String productDescriptionSort;
    private String productTiktok;
    private LocalDateTime productUntil;
    private String imageUrlSmall;
    private Long imageId;
    private OPrice price;
    private HashSet<OTag> tags;



    public ProductCard() {
    }


    public ProductCard(List<ITProduct> itProducts) {
        if(itProducts.size()>0){
            ITProduct itProduct = itProducts.get(0);
            this.productId = itProduct.getProductId();
            this.productName = itProduct.getProductName();
            this.productHot = itProduct.getProductHot();
            this.productRating = itProduct.getProductRating();
            this.productHot = itProduct.getProductHot();
            this.productDescriptionSort = itProduct.getProductDescriptionSort();
            this.productTiktok = itProduct.getProductTiktok();
            this.productUntil = itProduct.getProductUntil();
            this.imageId = itProduct.getImageId();
            this.imageUrlSmall = itProduct.getImageUrlSmall();
            this.price = new OPrice();

            this.price.setPriceId(itProduct.getPriceId());
            this.price.setPriceName(itProduct.getPriceName());
            this.price.setPricePrice(itProduct.getPricePrice());
            this.price.setPriceSalePrice(itProduct.getPriceSalePrice());
            this.price.setPriceStartAt(itProduct.getPriceStartAt());
            this.price.setPriceFinishAt(itProduct.getPriceFinishAt());
            this.price.setPriceSale(itProduct.getPriceSale());

            this.tags = new HashSet<OTag>();
            itProducts.forEach(p->{
                OTag oTag = new OTag(p.getTagId(),p.getTagName());
                if(!tags.contains(oTag)) tags.add(oTag);
            });
        }
    };

//    public List<ProductCard> toList(List<ITProduct> itProducts){
//
//        Set<Long> key = new HashSet<>();
//        Map<Long,List<ITProduct> > map = new HashMap();
//
//        itProducts.forEach(data->{
//            if(!key.contains(data.getProductId())){
//                key.add(data.getProductId());
//            }
//        });
//
//        key.forEach(k->{
//            map.put(k, new ArrayList<>());
//        });
//
//        itProducts.forEach(data->{
//            List<ITProduct> products = map.get(data.getProductId());
//            products.add(data);
//            map.replace(data.getProductId(),products );
//        });
//
//        map.forEach((k,data)->{
//            log.info(data.toString());
//        });
//
//        List<ProductCard> productCards = new ArrayList<>();
//
//        map.forEach((k,data)->{
//            productCards.add(new ProductCard(data));
//        });
//
//        return  productCards;
//
//    }



}
