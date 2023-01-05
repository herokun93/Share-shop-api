package share.shop.payloads.response.init;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import share.shop.payloads.response.ProductModeResponse;

import java.util.List;

@Getter
@Setter
@Builder
public class InitResponse {
    List<?> categories;
    List<?> features;
    List<?> topRated;
    List<?> topBestSelling;
    List<?> latest;
    List<?> products;
    List<ProductModeResponse> productModes;

}
