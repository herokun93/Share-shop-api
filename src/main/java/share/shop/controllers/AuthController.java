package share.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import share.shop.exceptions.AppException;
import share.shop.exceptions.ResourceNotFoundException;
import share.shop.models.Role;
import share.shop.models.RoleName;
import share.shop.models.User;
import share.shop.payloads.*;
import share.shop.repositories.RoleRepository;
import share.shop.repositories.UserRepository;
import share.shop.securities.JwtTokenProvider;
import share.shop.services.UserService;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;



    @Autowired
    JwtTokenProvider tokenProvider;

//    @PostMapping("/signIn")
//    public ResponseEntity<?> signIn(@Valid @RequestBody LoginRequest loginRequest) {
//        Optional<User> foundUser = userRepository.findByEmail(loginRequest.getEmail());
//
//        if(foundUser.isPresent()){
//
//            String email = loginRequest.getEmail();
//            String password = loginRequest.getPassword();;
//
//            User user = new User(email,password);
//
//            User userSave =
//
//            return
//        }
//
//        return registerUser(authRequest);
//    }

//    @PostMapping("/signUp")
//    public ResponseEntity<?> signUp(@Valid @RequestBody AuthRequest authRequest) {
//        Optional<User> foundUser = userRepository.findByEmail(authRequest.getEmail());
//
//        if (foundUser.isPresent()) {
//            return loginUser(foundUser.get(), authRequest);
//        }
//
//        return registerUser(authRequest);
//    }
//
//    @PostMapping("/signinOrSignup")
//    public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthRequest authRequest) {
//        System.out.println("signinOrSignup");
//        Optional<User> foundUser = userRepository.findByEmail(authRequest.getEmail());
//
//        if (foundUser.isPresent()) {
//            return loginUser(foundUser.get(), authRequest);
//        }
//
//        return registerUser(authRequest);
//    }

    @PostMapping(value = "/login")
    private ResponseEntity<?> loginUser(@Valid @RequestBody AuthRequest authRequest) {

        String email = authRequest.getEmail();

        User userGet = userService.findByEmail(email).orElseThrow(()-> {
            throw new ResourceNotFoundException("user","email",email);});

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserProfile userProfile = new UserProfile();


        String jwt = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, userProfile.userProfileConvert(userGet)));
    }

    @PostMapping(value = "/register",consumes = {"multipart/form-data"})
    private ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        String email = registerRequest.getEmail();
        if (userService.existsByEmail(email)) {
            return new ResponseEntity<>(new ApiResponse(false, "Email Address exist!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        String username = registerRequest.getUsername();
        String password = registerRequest.getPassword();

        User user = new User();
        user.newUser(email,username,password);

        User newUser = userService.createUserSendEmailActiveAccount(user);


        if (newUser != null) {
            return new ResponseEntity<>("Created user and sent active", HttpStatus.OK);
        }

        return new ResponseEntity<String>("Your register Failed", HttpStatus.EXPECTATION_FAILED);
    }

    @RequestMapping(value = "/verify/{token}", method = RequestMethod.GET)
    public ResponseEntity  verify(@PathVariable("token") String token){

        User getUser = userService.findByCreateToken(token).orElseThrow(()-> {
            throw new ResourceNotFoundException("User","createToken",token);});

        if(userService.userVerify(getUser)){
            return new ResponseEntity<>("Verify is ok", HttpStatus.OK);
        }

        return new ResponseEntity<>("verify is false", HttpStatus.OK);
    }
}
