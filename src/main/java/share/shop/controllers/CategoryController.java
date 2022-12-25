package share.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import share.shop.exceptions.ResourceNotFoundException;
import share.shop.models.Category;
import share.shop.payloads.request.CategoryRequest;
import share.shop.payloads.response.CategoriesResponse;
import share.shop.payloads.response.PagedResponse;
import share.shop.services.CategoryService;
import share.shop.services.ProductService;
import share.shop.utils.AppConstants;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @PostMapping(value = "/categories")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> postCategory(@Valid @RequestBody CategoryRequest categoryRequest) {

        String name = categoryRequest.getName();

        Optional<Category> categoryGet = categoryService.findByName(name);

        if(!categoryGet.isEmpty())  return new ResponseEntity(null, HttpStatus.BAD_REQUEST);

        Category categoryNew = Category.builder().name(categoryRequest.getName()).build();

        if(Objects.isNull(categoryNew))  return new ResponseEntity(null, HttpStatus.BAD_REQUEST);

        Category category = categoryService.save(categoryNew);

        if(Objects.isNull(category))  return new ResponseEntity(null, HttpStatus.BAD_REQUEST);

        return new ResponseEntity(category, HttpStatus.OK);
    }

    @PutMapping(value = "/categories/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> putCategory(
            @Valid  @PathVariable("id") @Min(0) Long categoryId,
            @Valid @RequestBody CategoryRequest categoryRequest) {

        String name = categoryRequest.getName();


        Category category = categoryService.findById(categoryId).orElseThrow(()-> {
                    throw new ResourceNotFoundException("Category","id",categoryId);});

        Optional<Category> categoryGet =categoryService.findByName(name);

        if(!categoryGet.isEmpty())  return new ResponseEntity("Name is exist", HttpStatus.BAD_REQUEST);

        category.setName(name);
        category = categoryService.save(category);

        if(Objects.isNull(category))  return new ResponseEntity(null, HttpStatus.BAD_REQUEST);

        return new ResponseEntity(category, HttpStatus.OK);
    }

    @GetMapping(value="/categories")
    public PagedResponse getAllCategories(
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return categoryService.getAllCategories(page,size);
    }

    @PreAuthorize("hasAnyRole('PARTNER','ADMIN','USER','STAFF')")
    @GetMapping(value="/categories/sub")
    public ResponseEntity<?> getAllSubCategoryOfCategory() {

        List<Category> categories = categoryService.findAll();
        CategoriesResponse categoriesResponse = new CategoriesResponse(categories);
        if(Objects.isNull(categoriesResponse))return new ResponseEntity(null,HttpStatus.BAD_REQUEST);
        return new ResponseEntity(categoriesResponse,HttpStatus.OK);
    }

    @GetMapping(value="/categories/test")
    public ResponseEntity test(){
        return ResponseEntity.ok(categoryService.test());
    }


    @GetMapping(value="/categories/{id}/subCategory")
    public PagedResponse getAllSubcategoryOfACategory(
            @PathVariable("id") @Min(0) int categoryId,
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return categoryService.getAllSubCategoryOfCategory(categoryId,page,size);
    }
    @GetMapping(value="/categories/{id}/pr")
    public PagedResponse getTopProductCategory(
            @RequestParam("mode") @Min(0) int mode,
            @PathVariable("id") @Min(0) int categoryId,
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return productService.getAllProductsModeAndEnable(1,page,size,true);
    }

//    @GetMapping(value="/categories/{search}/subCate")
//    public PagedResponse getAllSubcategoryOfACategoryBySearch(
//            @PathVariable("search") @NotBlank String search,
//            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
//            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
//        return categoryService.getAllSubCategoryOfCategoryBySelect(search,page,size);
//    }


//    @GetMapping(value="/categories/subCategory/{id}")
//    public ResponseEntity<?> getAllSubcategoryOfCategory( @PathVariable("id") @Min(0) Long categoryId) {
//        Category category = categoryService.findById(categoryId).orElseThrow(()->{
//            throw new ResourceNotFoundException("category","id",categoryId);
//        });
//        CategoryAndSubResponse categoryAndSubResponse = new CategoryAndSubResponse();
//        return new ResponseEntity<>(categoryAndSubResponse.categoryConvert(category),HttpStatus.OK);
//    }

}
