package share.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import share.shop.exceptions.ResourceNotFoundException;
import share.shop.models.Category;
import share.shop.models.User;
import share.shop.payloads.*;
import share.shop.repositories.ProductRepository;
import share.shop.repositories.UserRepository;
import share.shop.securities.CurrentUser;
import share.shop.securities.UserPrincipal;
import share.shop.services.ProductService;
import share.shop.utils.AppConstants;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductService productService;

    @GetMapping("/users/me")
    public UserProfile getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        User user = userRepository.findByEmail(currentUser.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User", "Email", currentUser.getEmail()));

        UserProfile userProfile = new UserProfile();

        return userProfile.userProfileConvert(user);
    }

    @GetMapping("/users/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping(value="/users/{id}/shop")
    public PagedResponse getAllProductsForCreated(
            @PathVariable("id") @Min(0) long createdId,
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return productService.getAllProductsForCreated(createdId,page,size);
    }


    @PutMapping("/users/{id}/info")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> putUserInfo(
            @Valid @PathVariable("id") @Min(0) Long userId,
            @Valid @RequestBody UserProfileUpdateRequest newUser) {

       // String name = categoryRequest.getName();

//
//        Category category = categoryService.findById(categoryId).orElseThrow(()-> {
//            throw new ResourceNotFoundException("Category","id",categoryId);});
//
//        Optional<Category> categoryGet =categoryService.findByName(name);
//
//        if(!categoryGet.isEmpty())  return new ResponseEntity("Name is exist", HttpStatus.BAD_REQUEST);
//
//        category.setName(name);
//        category = categoryService.save(category);
//
//        if(Objects.isNull(category))  return new ResponseEntity(null, HttpStatus.BAD_REQUEST);

        return new ResponseEntity(null, HttpStatus.OK);
    }

    @PutMapping("/users/{id}/pass")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> putUserPass(
            @Valid @PathVariable("id") @Min(0) Long userId,
            @Valid @RequestBody UserUpdatePassRequest newUser) {

        // String name = categoryRequest.getName();

//
//        Category category = categoryService.findById(categoryId).orElseThrow(()-> {
//            throw new ResourceNotFoundException("Category","id",categoryId);});
//
//        Optional<Category> categoryGet =categoryService.findByName(name);
//
//        if(!categoryGet.isEmpty())  return new ResponseEntity("Name is exist", HttpStatus.BAD_REQUEST);
//
//        category.setName(name);
//        category = categoryService.save(category);
//
//        if(Objects.isNull(category))  return new ResponseEntity(null, HttpStatus.BAD_REQUEST);

        return new ResponseEntity(null, HttpStatus.OK);
    }

    @PutMapping("/users/{id}/email")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> putUserEmail(
            @Valid @PathVariable("id") @Min(0) Long userId,
            @Valid @RequestBody UserUpdateEmailRequest newUser) {

        // String name = categoryRequest.getName();

//
//        Category category = categoryService.findById(categoryId).orElseThrow(()-> {
//            throw new ResourceNotFoundException("Category","id",categoryId);});
//
//        Optional<Category> categoryGet =categoryService.findByName(name);
//
//        if(!categoryGet.isEmpty())  return new ResponseEntity("Name is exist", HttpStatus.BAD_REQUEST);
//
//        category.setName(name);
//        category = categoryService.save(category);
//
//        if(Objects.isNull(category))  return new ResponseEntity(null, HttpStatus.BAD_REQUEST);

        return new ResponseEntity(null, HttpStatus.OK);
    }
}
