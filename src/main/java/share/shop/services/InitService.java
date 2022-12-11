package share.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import share.shop.payloads.response.InitResponse;

@Service
public class InitService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    public InitResponse home(){


        InitResponse initResponse = InitResponse.builder()
                .categories(categoryService.getAllCategories(0,10).getContent())
                .features(productService.getAllProductsForFeatured(1,0,20).getContent())
                .products(productService.getAllProductsForShop(Long.valueOf(1),0,20).getContent())
                .build();

        return initResponse;


    }
}
