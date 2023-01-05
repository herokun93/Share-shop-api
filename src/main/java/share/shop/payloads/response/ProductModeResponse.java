package share.shop.payloads.response;

import lombok.*;
import org.modelmapper.ModelMapper;
import share.shop.models.ProductMode;
import share.shop.models.Shop;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductModeResponse {
    private Long id;
    private String name;
    private List<ProductCardResponse> products = new ArrayList<>();

    public ProductModeResponse setProductModeResponse(ProductMode productMode){
        ModelMapper modelMapper = new ModelMapper();
        ProductModeResponse productModeResponse = modelMapper.map(productMode, ProductModeResponse.class);
        products = new ArrayList<>();
        productMode.getProducts().forEach(product -> {
            products.add(new ProductCardResponse().productCardConvert(product));
        });
        productModeResponse.setProducts(products);
        return  productModeResponse;
    }

    public List<ProductModeResponse> setProductModesResponse(List<ProductMode> productModes){


        List<ProductModeResponse> productModeResponses = new ArrayList<>();

        productModes.forEach(productMode -> {

            ModelMapper modelMapper = new ModelMapper();
            ProductModeResponse productModeResponse = modelMapper.map(productMode, ProductModeResponse.class);

            List<ProductCardResponse> IProducts = new ArrayList<>();

            productMode.getProducts().forEach(product -> {
                IProducts.add(new ProductCardResponse().productCardConvert(product));
            });
            productModeResponse.setProducts(IProducts);

            productModeResponses.add(productModeResponse);

        });

        return  productModeResponses;
    }
}
