package share.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import share.shop.exceptions.ResourceNotFoundException;
import share.shop.models.User;
import share.shop.payloads.UserIdentityAvailability;
import share.shop.payloads.UserProfile;
import share.shop.repositories.UserRepository;
import share.shop.securities.CurrentUser;
import share.shop.securities.UserPrincipal;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserRepository userRepository;

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
}
