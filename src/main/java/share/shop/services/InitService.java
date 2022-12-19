package share.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

        List<Category> categories = categoryService.findAllByEnable(Sort.by("id").ascending(),true);
        categories.forEach(e->{
            CategoryResponse categoryResponse = new CategoryResponse();


            categoryResponses.add(categoryResponse.getAllSubCategoryOfACategory(e));
        });




        InitResponse initResponse = InitResponse.builder()
                .categories(categoryResponses)
                .features(productService.getAllProductsModeAndEnable(1,0,20,true).getContent())
                .products(productService.getAllProductsForShop(Long.valueOf(1),0,20).getContent())
                .build();


        return initResponse;


    }
}
