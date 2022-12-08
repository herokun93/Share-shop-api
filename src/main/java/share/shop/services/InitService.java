package share.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import share.shop.payloads.InitResponse;
import share.shop.repositories.CategoryRepository;
import share.shop.repositories.ProductRepository;

import java.util.List;

@Service
public class InitService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;


    public InitResponse home(){


        InitResponse initResponse = InitResponse.builder()
                .categorises(categoryService.getAllCategories(0,10).getContent())
                .features(productService.getAllProductsForFeatured(1,0,20).getContent())
                .build();

        return initResponse;


    }
}
