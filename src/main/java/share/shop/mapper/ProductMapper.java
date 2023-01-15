package share.shop.mapper;

import org.modelmapper.ModelMapper;
import share.shop.dto.ProductCardDto;
import share.shop.dto.ProductDetailDto;
import share.shop.dto.card.ImageCardDto;
import share.shop.dto.card.PriceCardDto;
import share.shop.dto.card.TagCardDto;
import share.shop.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {
    public static ProductCardDto cardConvert(Product product) {
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
    public static List<ProductCardDto> toListCards(List<Product> products) {

        List<ProductCardDto> productCardDtoList = new ArrayList<>();
        products.forEach(product -> {
            productCardDtoList.add(cardConvert(product));
        });
        return productCardDtoList;
    }

    public static ProductDetailDto toDetail(Product product) {
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
