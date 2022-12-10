package share.shop.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import share.shop.models.Category;
import share.shop.models.SubCategory;
import share.shop.payloads.response.PagedResponse;
import share.shop.payloads.request.SubCategoryRequest;
import share.shop.payloads.response.SubCategoryResponse;
import share.shop.services.CategoryService;
import share.shop.services.SubCategoryService;
import share.shop.utils.AppConstants;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Objects;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class SubCategoryController {

    @Autowired
    private SubCategoryService subCategoryService;

    @Autowired
    private CategoryService categoryService;



    @ResponseBody
    @PostMapping(value = "/subCategories")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity postSubCategory(@RequestBody @Valid SubCategoryRequest subCategoryRequest){

        String name = subCategoryRequest.getName();
        Long categoryId = subCategoryRequest.getCategoryId();

        Optional<Category> categoryGet = categoryService.findById(categoryId);
        if(!categoryGet.isEmpty()) return new ResponseEntity(null, HttpStatus.BAD_REQUEST);

        if(!subCategoryService.existsByName(name)) return new ResponseEntity(null, HttpStatus.BAD_REQUEST);

        SubCategory subCategoryNew = SubCategory.builder()
                .name(name)
                .category(categoryGet.get())
                .build();

        SubCategory subCategory = subCategoryService.save(subCategoryNew);
        if(Objects.isNull(subCategory)) return new ResponseEntity(null, HttpStatus.BAD_REQUEST);

        SubCategoryResponse subCategoryResponse = new SubCategoryResponse();
        return new ResponseEntity(subCategoryResponse.subCategoryConvert(subCategory), HttpStatus.OK);
    }

    @PutMapping(value = "/subCategories/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> putSubCategory(
            @Valid  @PathVariable("id") @Min(0) Long subCategoryId,
            @Valid @RequestBody SubCategoryRequest subCategoryRequest) {

        String name = subCategoryRequest.getName();
        Long categoryId = subCategoryRequest.getCategoryId();



        Optional<Category> categoryGet = categoryService.findById(categoryId);

        if(categoryGet.isEmpty())  return new ResponseEntity(null, HttpStatus.BAD_REQUEST);

        Optional<SubCategory> subCategoryGet = subCategoryService.findById(subCategoryId);

        if(subCategoryGet.isEmpty())  return new ResponseEntity(null, HttpStatus.BAD_REQUEST);

        subCategoryGet = subCategoryService.findByName(name);

        if(!subCategoryGet.isEmpty()) return new ResponseEntity(null, HttpStatus.BAD_REQUEST);


        SubCategory subCategoryNew = SubCategory.builder()
                .name(name)
                .id(subCategoryId)
                .category(categoryGet.get())
                .build();

        if(Objects.isNull(subCategoryNew))  return new ResponseEntity(null, HttpStatus.BAD_REQUEST);

        SubCategory subCategory = subCategoryService.save(subCategoryNew);

        if(Objects.isNull(subCategory))  return new ResponseEntity(null, HttpStatus.BAD_REQUEST);

        return new ResponseEntity(subCategory, HttpStatus.OK);
    }

    @GetMapping(value="/subCategories")
    public PagedResponse getAllSubCategories(
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return subCategoryService.getAllSubCategories(page,size);
    }

    @GetMapping(value="/subCategories/{id}/products")
    public PagedResponse getAllProductForSubCategory(
            @PathVariable("id") @Min(0) int id,
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return subCategoryService.getAllProductForSubCategory(id,page,size);
    }


}
