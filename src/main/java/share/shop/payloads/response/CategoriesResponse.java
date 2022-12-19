package share.shop.payloads.response;

import share.shop.models.Category;

import java.util.ArrayList;
import java.util.List;


public class CategoriesResponse {

    public List<CategoryResponse> categoriesResponse;
    public CategoriesResponse(List<Category> categoryList) {
        categoriesResponse = new ArrayList<>();
         categoryList.forEach(c->{
             CategoryResponse categoryResponse = new CategoryResponse().getAllSubCategoryOfACategory(c);
             categoriesResponse.add(categoryResponse);
         });
    }

    public List<CategoryResponse> getCategoriesResponse() {
        return categoriesResponse;
    }
}
