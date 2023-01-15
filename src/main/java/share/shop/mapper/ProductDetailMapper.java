package share.shop.mapper;



import org.modelmapper.ModelMapper;
import share.shop.dto.ImageDto;
import share.shop.dto.PriceDto;
import share.shop.dto.ProductDetailDto;
import share.shop.dto.TagDto;
import share.shop.dto.card.ImageCardDto;
import share.shop.dto.card.PriceCardDto;
import share.shop.dto.card.TagCardDto;
import share.shop.models.Product;

import java.util.stream.Collectors;

public class ProductDetailMapper {

    public static ProductDetailDto convert(Product product) {
        ModelMapper modelMapper = new ModelMapper();

        ProductDetailDto productDetailDto = modelMapper.map(product, ProductDetailDto.class);

        productDetailDto.setImages(product.getImages().stream()
                .map(images -> modelMapper.map(images, ImageCardDto.class)).collect(Collectors.toList()));



        productDetailDto.setTags(product.getTags().stream()
                .map(tags -> modelMapper.map(tags, TagCardDto.class)).collect(Collectors.toList()));

        productDetailDto.setPrices(product.getPrices().stream()
                .map(prices -> modelMapper.map(prices, PriceCardDto.class)).collect(Collectors.toList()));

        return productDetailDto;
    }
}
