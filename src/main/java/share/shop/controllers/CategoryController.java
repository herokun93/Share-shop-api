package share.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import share.shop.models.Category;
import share.shop.models.User;
import share.shop.payloads.AuthRequest;
import share.shop.payloads.CategoryRequest;
import share.shop.services.CategoryService;

import javax.validation.Valid;
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
}
