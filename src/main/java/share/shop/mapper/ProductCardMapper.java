package share.shop.mapper;

import org.modelmapper.ModelMapper;
import share.shop.dto.PriceDto;
import share.shop.dto.ProductCardDto;
import share.shop.dto.TagDto;
import share.shop.dto.card.ImageCardDto;
import share.shop.dto.card.PriceCardDto;
import share.shop.dto.card.TagCardDto;
import share.shop.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductCardMapper {

    public static  ProductCardDto convert(Product product) {
        ModelMapper modelMapper = new ModelMapper();

        ProductCardDto productCardDto = modelMapper.map(product,ProductCardDto.class);
        productCardDto.setImages(product.getImages().stream()
                .map(images -> modelMapper.map(images, ImageCardDto.class)).collect(Collectors.toList()));

        productCardDto.setTags(product.getTags().stream()
                .map(tags -> modelMapper.map(tags, TagCardDto.class)).collect(Collectors.toList()));

        productCardDto.setPrices(product.getPrices().stream()
                .map(prices -> modelMapper.map(prices, PriceCardDto.class)).collect(Collectors.toList()));

        return productCardDto;
    }
    public static List<ProductCardDto> listConvert(List<Product> products) {

        List<ProductCardDto> productCardDtoList = new ArrayList<>();
        products.forEach(product -> {
            productCardDtoList.add(convert(product));
        });
        return productCardDtoList;
    }
}
