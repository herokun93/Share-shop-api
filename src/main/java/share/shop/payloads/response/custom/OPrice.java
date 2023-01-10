package share.shop.payloads.response.custom;

import lombok.*;

import java.time.Instant;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OPrice {
    private Long priceId;
    private String priceName;
    private long pricePrice;
    private long priceSalePrice;
    private Instant priceStartAt;
    private Instant priceFinishAt;
    private boolean priceSale;
}
