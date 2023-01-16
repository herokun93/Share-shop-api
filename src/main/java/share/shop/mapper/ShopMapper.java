package share.shop.mapper;

import org.modelmapper.ModelMapper;
import share.shop.dto.ShopViewDto;
import share.shop.models.Shop;

public class ShopMapper {

    public static ShopViewDto toShopInfo(Shop shop){
        ModelMapper modelMapper =  new ModelMapper();

        ShopViewDto shopViewDto = modelMapper.map(shop,ShopViewDto.class);
        return shopViewDto;

    }
}
