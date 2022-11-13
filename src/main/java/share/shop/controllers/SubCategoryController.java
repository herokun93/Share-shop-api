package share.shop.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import share.shop.models.Category;
import share.shop.models.SubCategory;
import share.shop.payloads.SubCategoryRequest;
import share.shop.payloads.SubCategoryResponse;
import share.shop.repositories.CategoryRepository;
import share.shop.repositories.SubCategoryRepository;
import share.shop.services.CategoryService;
import share.shop.services.SubCategoryService;

import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class SubCategoryController {

    @Autowired
    private SubCategoryService subCategoryService;

    @Autowired
    private CategoryService categoryService;

    @ResponseBody
    @PostMapping("/subCategories")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity postSubCategory(@RequestBody @Valid SubCategoryRequest subCategoryRequest){


        Optional<Category> categoryNew = categoryService.findById(subCategoryRequest.getCategoryId());

        SubCategory subCategoryNew = SubCategory.builder()
                .name(subCategoryRequest.getName())
                .category(categoryNew.get())
                .build();

        SubCategory subCategory = subCategoryService.save(subCategoryNew);
        SubCategoryResponse subCategoryResponse = new SubCategoryResponse();
        return new ResponseEntity(subCategoryResponse.subCategoryConvert(subCategory), HttpStatus.OK);
    }


}
