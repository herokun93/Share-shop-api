package share.shop.payloads;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class InitResponse {
    List<?> categorises;
    List<?> features;
    List<?> products;
}
