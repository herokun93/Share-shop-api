package share.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import share.shop.exceptions.AppException;
import share.shop.models.Role;
import share.shop.models.RoleName;
import share.shop.models.User;
import share.shop.payloads.ApiResponse;
import share.shop.payloads.AuthRequest;
import share.shop.payloads.JwtAuthenticationResponse;
import share.shop.payloads.UserProfile;
import share.shop.repositories.RoleRepository;
import share.shop.repositories.UserRepository;
import share.shop.securities.JwtTokenProvider;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signinOrSignup")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthRequest authRequest) {
        System.out.println("signinOrSignup");
        Optional<User> foundUser = userRepository.findByEmail(authRequest.getEmail());

        if (foundUser.isPresent()) {
            return loginUser(foundUser.get(), authRequest);
        }

        return registerUser(authRequest);
    }

    private ResponseEntity<?> loginUser(User user, AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserProfile userProfile = new UserProfile();


        String jwt = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, userProfile.userProfileConvert(user)));
    }

    private ResponseEntity<?> registerUser(AuthRequest authRequest) {
        System.out.println("Register new user");
        if (userRepository.existsByEmail(authRequest.getEmail())) {
            return new ResponseEntity<>(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(authRequest.getEmail(), authRequest.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        User newUser = userRepository.save(user);

        if (newUser != null) {
            return loginUser(newUser, authRequest);
        }

        return new ResponseEntity<String>("Your Signup Failed", HttpStatus.EXPECTATION_FAILED);
    }
}
