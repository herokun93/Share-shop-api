package share.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import share.shop.exceptions.ResourceNotFoundException;
import share.shop.models.User;
import share.shop.payloads.PagedResponse;
import share.shop.payloads.UserIdentityAvailability;
import share.shop.payloads.UserProfile;
import share.shop.repositories.ProductRepository;
import share.shop.repositories.UserRepository;
import share.shop.securities.CurrentUser;
import share.shop.securities.UserPrincipal;
import share.shop.services.ProductService;
import share.shop.utils.AppConstants;

import javax.validation.constraints.Min;

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
}
