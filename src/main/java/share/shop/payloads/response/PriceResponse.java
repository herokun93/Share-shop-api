package share.shop.payloads.response;

import lombok.*;
import org.modelmapper.ModelMapper;
import share.shop.models.Image;
import share.shop.models.Price;
import share.shop.utils.ImageToUrl;

import java.time.Instant;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriceResponse {
    private Long id;
    private String name;
    private long price;
    private long salePrice;
    private Instant startAt;
    private Instant finishAt;

    public PriceResponse priceResponseConvert(Price price){
        ModelMapper modelMapper = new ModelMapper();
        PriceResponse priceResponse = modelMapper.map(price,PriceResponse.class);
        return  priceResponse;
    }
}
