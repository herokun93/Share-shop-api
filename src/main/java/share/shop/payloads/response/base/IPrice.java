package share.shop.payloads.response.base;

import java.time.Instant;

public interface IPrice {
    Long getPriceId();
    String getPriceName();
    long getPricePrice();
    long getPriceSalePrice();
    Instant getStartAt();
    Instant getSaleFinishAt();
    boolean getPriceSale();
}
