package share.shop.payloads.response.init;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
;

@Getter
@Setter
@Builder
public class InitNewPrResponse {
    Object categories;
    Object countries;
}
