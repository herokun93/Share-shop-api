package share.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import share.shop.exceptions.ResourceNotFoundException;
import share.shop.models.Category;
import share.shop.models.User;
import share.shop.payloads.*;
import share.shop.repositories.ProductRepository;
import share.shop.repositories.UserRepository;
import share.shop.securities.CurrentUser;
import share.shop.securities.UserLogged;
import share.shop.securities.UserPrincipal;
import share.shop.services.ProductService;
import share.shop.services.UserService;
import share.shop.utils.AppConstants;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/users/me")
    public UserProfile getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        User user = userService.findByEmail(currentUser.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User", "Email", currentUser.getEmail()));

        UserProfile userProfile = new UserProfile();

        return userProfile.userProfileConvert(user);
    }

    @GetMapping("/users/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userService.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping(value="/users/{id}/shop")
    public PagedResponse getAllProductsForCreated(
            @PathVariable("id") @Min(0) long createdId,
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return productService.getAllProductsForCreated(createdId,page,size);
    }


    @PutMapping("/users/info")
    public ResponseEntity<?> putUserInfo(
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

    @PutMapping("/users/pass")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> putUserPass(
            @Valid @RequestBody UserUpdatePassRequest userPass) {

        // String name = categoryRequest.getName();
        String passNew = userPass.getPasswordNew();
        String passOld = userPass.getPasswordOld();


        UserLogged userLogged = new UserLogged();
        String email = userLogged.getEmail();
        User user = userService.findByEmailAndPassword(email,passwordEncoder.encode(passOld)).orElseThrow(
                () -> new ResourceNotFoundException("User", "password", passOld));

        user.setPassword(passwordEncoder.encode(passNew));

        user = userService.saveAndFlush(user);
        if(Objects.isNull(user)){
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(null, HttpStatus.OK);
    }

    @PutMapping("/users/name")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> putUserUserName(
            @Valid @RequestBody UserUpdateNameRequest newNameUser) {

            String name = newNameUser.getUsername();

            UserLogged userLogged = new UserLogged();
            String email = userLogged.getEmail();
            User user = userService.findByEmail(email).orElseThrow(
                    () -> new ResourceNotFoundException("User", "Email", ""));

            user.setUsername(name);

            user = userService.saveAndFlush(user);
            if(Objects.isNull(user)){
                return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
            }

        return new ResponseEntity(null, HttpStatus.OK);
    }

}
