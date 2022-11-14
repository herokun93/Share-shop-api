package share.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import share.shop.models.Category;
import share.shop.models.User;
import share.shop.payloads.AuthRequest;
import share.shop.payloads.CategoryRequest;
import share.shop.payloads.PagedResponse;
import share.shop.services.CategoryService;
import share.shop.utils.AppConstants;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/categories")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody CategoryRequest categoryRequest) {

        String name = categoryRequest.getName();

        Optional<Category> categoryGet = categoryService.findByName(name);

        if(!categoryGet.isEmpty())  return new ResponseEntity(null, HttpStatus.BAD_REQUEST);

        Category categoryNew = Category.builder().name(categoryRequest.getName()).build();

        if(Objects.isNull(categoryNew))  return new ResponseEntity(null, HttpStatus.BAD_REQUEST);

        Category category = categoryService.save(categoryNew);

        if(Objects.isNull(category))  return new ResponseEntity(null, HttpStatus.BAD_REQUEST);

        return new ResponseEntity(category, HttpStatus.OK);
    }

    @GetMapping(value="/categories")
    public PagedResponse getAllCategories(
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return categoryService.getAllCategories(page,size);
    }

    @GetMapping(value="/categories/{id}/subCategory")
    public PagedResponse getAllSubcategoryOfCategory(
            @PathVariable("id") @Min(0) int categoryId,
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return categoryService.getAllSubCategoryOfCategory(categoryId,page,size);
    }

}
