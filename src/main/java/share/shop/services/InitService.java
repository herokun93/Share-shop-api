package share.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import share.shop.dto.ProductCardDto;
import share.shop.models.Category;
import share.shop.models.Country;
import share.shop.models.Product;
import share.shop.models.ProductMode;
import share.shop.payloads.response.CategoriesResponse;
import share.shop.payloads.response.CategoryResponse;
import share.shop.payloads.response.CountriesResponse;
import share.shop.payloads.response.ProductModeResponse;
import share.shop.payloads.response.init.InitNewPrResponse;
import share.shop.payloads.response.init.InitResponse;
import share.shop.repositories.ProductModeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class InitService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CountryService countryService;


    @Autowired
    private ProductModeRepository productModeRepository;



    @Autowired
    private ProductService productService;




    public InitResponse home(){



        List<CategoryResponse>  categoryResponses = new ArrayList<>();

        List<Category> categories = categoryService.findAllByEnable(Sort.by("id").ascending(),true);
        categories.forEach(e->{
            CategoryResponse categoryResponse = new CategoryResponse();


            categoryResponses.add(categoryResponse.getAllSubCategoryOfACategory(e));
        });


        List<ProductMode> productModes = productModeRepository.findAll();




//        InitResponse initResponse = InitResponse.builder()
//                .productModes(new ProductModeResponse().setProductModesResponse(productModes))
//                .categories(categoryResponses)
//                .build();







//        InitResponse initResponse = InitResponse.builder()
//                .productModes(new ProductModeResponse().setProductModesResponse(productModes))
//                .categories(categoryResponses)
//                .features(productService.getAllProductsByProductModeAndEnable(1,0,20,true).getContent())
//                .products(productService.getAllProductsForShop(Long.valueOf(1),0,20).getContent())
//                .build();

        InitResponse initResponse = InitResponse.builder()
                .productModes(new ProductModeResponse().setProductModesResponse(productModes))
                .categories(categoryResponses)
                .features(productService.getAllProductsByProductModeAndEnable(1,0,20,true).getContent())
                .products(productService.getAllProductsForShop(Long.valueOf(1),0,20).getContent())
                .build();


        return initResponse;


    }
    public InitNewPrResponse newProduct(){
        List<Category> categories = categoryService.findAll();
        List<Country> countries = countryService.findAll();
        //CategoriesResponse categoriesResponse = new CategoriesResponse(categories);

        InitNewPrResponse newPrResponse = InitNewPrResponse
                .builder()
                .categories(new CategoriesResponse(categories).getCategoriesResponse())
                .countries(new CountriesResponse(countries).getCountriesResponse())
                .build();
        return  newPrResponse;
    }
}
