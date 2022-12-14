package share.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import share.shop.models.Category;
import share.shop.payloads.response.CategoryResponse;
import share.shop.payloads.response.InitResponse;

import java.util.ArrayList;
import java.util.List;

@Service
public class InitService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    public InitResponse home(){

        List<CategoryResponse>  categoryResponses = new ArrayList<>();

        List<Category> categories = categoryService.findAll();
        categories.forEach(e->{
            CategoryResponse categoryResponse = new CategoryResponse();

            categoryResponses.add(categoryResponse.getAllSubCategoryOrAllCategory(e));
        });



//        InitResponse initResponse = InitResponse.builder()
//                .categories(categoryService.getAllCategories(0,10).getContent())
//                .features(productService.getAllProductsForFeatured(1,0,20).getContent())
//                .products(productService.getAllProductsForShop(Long.valueOf(1),0,20).getContent())
//                .subCategory(categoryResponses)
//                .build();

        InitResponse initResponse = InitResponse.builder()
                .categories(categoryResponses)
                .features(productService.getAllProductsForFeatured(1,0,20).getContent())
                .products(productService.getAllProductsForShop(Long.valueOf(1),0,20).getContent())
                .build();


        return initResponse;


    }
}
