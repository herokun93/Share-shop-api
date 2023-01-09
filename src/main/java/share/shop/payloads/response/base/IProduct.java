package share.shop.payloads.response.base;

import java.time.LocalDateTime;

public interface IProduct {
    Long getProductId();
    String getProductName();
    boolean getProductHot();
    int getProductRating();
    String getProductDescription();
    String getProductDescriptionSort();
    String getProductTiktok();
    boolean getProductEnable();
    int getProductMode();
    LocalDateTime getProductUntil();

}
