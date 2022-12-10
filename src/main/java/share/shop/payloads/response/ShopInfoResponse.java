package share.shop.payloads.response;

import lombok.*;
import org.modelmapper.ModelMapper;
import share.shop.models.Shop;

@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopInfoResponse {
    private long id;
    private String name;
    private String number;
    private String emailShop;
    private String address;

    public ShopInfoResponse shopInfoResponse(Shop shop){
        ModelMapper modelMapper = new ModelMapper();
        ShopInfoResponse shopInfoResponse = modelMapper.map(shop, ShopInfoResponse.class);
        return  shopInfoResponse;
    }
}
