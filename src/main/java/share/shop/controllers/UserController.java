package share.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import share.shop.exceptions.ResourceNotFoundException;
import share.shop.models.User;
import share.shop.payloads.request.UserProfile;
import share.shop.payloads.request.UserUpdateNameRequest;
import share.shop.payloads.request.UserUpdatePassRequest;
import share.shop.payloads.response.PagedResponse;
import share.shop.securities.CurrentUser;
import share.shop.securities.UserLogged;
import share.shop.securities.UserPrincipal;
import share.shop.services.CommentService;
import share.shop.services.ProductService;
import share.shop.services.UserService;
import share.shop.utils.AppConstants;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CommentService commentService;

    @GetMapping("/users/me")
    public UserProfile getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        User user = userService.findByEmail(currentUser.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User", "Email", currentUser.getEmail()));

        UserProfile userProfile = new UserProfile();

        return userProfile.userProfileConvert(user);
    }

//    @GetMapping("/users/checkEmailAvailability")
//    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
//        Boolean isAvailable = !userService.existsByEmail(email);
//        return new UserIdentityAvailability(isAvailable);
//    }

    @GetMapping(value="/users/{id}/shop")
    public PagedResponse getAllProductsForCreated(
            @PathVariable("id") @Min(0) long createdId,
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return productService.getAllProductsForCreated(createdId,page,size);
    }

    @GetMapping(value="/users/comments")
    @PreAuthorize("hasRole('USER')")
    public PagedResponse getAllCommentsByProductId(
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        UserLogged userLogged = new UserLogged();
        User user = userService.findByEmail(userLogged.getEmail()).orElseThrow(
                ()-> new ResourceNotFoundException("User","email",userLogged.getEmail())
        );
        return commentService.findAllCommentsByUserId(user.getId(),page,size);
    }


//    @PutMapping(value = "/users/info")
//    public ResponseEntity<?> putUserInfo(
//            @Valid @RequestBody UserProfileUpdateRequest newUser) {
//
//        return new ResponseEntity(null, HttpStatus.OK);
//    }


    @PutMapping(value = "/users/pass")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> putUserPass(

             @RequestBody  @Valid UserUpdatePassRequest userPass) {

        String passNew = userPass.getPasswordNew();
        String passOld = userPass.getPasswordOld();


        UserLogged userLogged = new UserLogged();
        String email = userLogged.getEmail();
        User user = userService.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("User", "password", passOld));

        if(passwordEncoder.matches(passOld,user.getPassword())){
            user.setPassword(passwordEncoder.encode(passNew));

            user = userService.saveAndFlush(user);
            if(Objects.isNull(user)){
                return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity(null, HttpStatus.OK);
        }else {
            return new ResponseEntity("Old password is wrong", HttpStatus.BAD_REQUEST);
        }


    }

    @PutMapping(value = "/users/name")
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
                return new ResponseEntity("Cannot update password", HttpStatus.BAD_REQUEST);
            }

        return new ResponseEntity(null, HttpStatus.OK);
    }

}
