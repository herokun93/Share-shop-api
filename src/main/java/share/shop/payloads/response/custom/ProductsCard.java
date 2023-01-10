package share.shop.payloads.response.custom;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import share.shop.payloads.response.icustom.ITProduct;

import java.util.*;

@Getter
@Setter
@ToString
@Slf4j
public class ProductsCard {
    private List<ProductCard> productCards;
    public ProductsCard(List<ITProduct> itProducts) {
        Set<Long> key = new HashSet<>();
        Map<Long,List<ITProduct> > map = new HashMap();

        itProducts.forEach(data->{
            if(!key.contains(data.getProductId())){
                key.add(data.getProductId());
            }
        });

        key.forEach(k->{
            map.put(k, new ArrayList<>());
        });

        itProducts.forEach(data->{
            List<ITProduct> products = map.get(data.getProductId());
            products.add(data);
            map.replace(data.getProductId(),products );
        });

        map.forEach((k,data)->{
            log.info(data.toString());
        });

        productCards = new ArrayList<>();

        map.forEach((k,data)->{
            productCards.add(new ProductCard(data));
        });

    }
}
