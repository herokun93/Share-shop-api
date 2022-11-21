package share.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import share.shop.exceptions.AppException;
import share.shop.models.Role;
import share.shop.models.RoleName;
import share.shop.models.User;
import share.shop.repositories.RoleRepository;
import share.shop.repositories.UserRepository;
import share.shop.utils.NotificationEmail;

import java.time.Instant;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final MailService mailService;


    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }
    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public boolean existsByEmail(String email) {return  userRepository.existsByEmail(email);}
    public User save(User user){return  userRepository.save(user);}

    public User saveAndFlush(User user){return  userRepository.saveAndFlush(user);}

    public Optional<User> findByCreateToken(String token){return userRepository.findByCreateToken(token);}

    public Optional<User>findByEmailAndPassword(String email,String password){return userRepository.findByEmailAndPassword(email,password);};





    public User createUserSendEmailActiveAccount(User user){

        String verificationToken  = UUID.randomUUID().toString();

        String passNew = passwordEncoder.encode(user.getPassword());

        user.setPassword(passNew);
        user.setCreateToken(verificationToken);
        user.setCreateExpiry(Instant.now().plusMillis(10 *60 *1000));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        mailService.sendMail(new NotificationEmail(
                "Please Activate your Account",
                user.getEmail(),
                "Thank you for signing up to heyShop, " +
                        "please click on the below url to activate your account : " +
                        "http://localhost:2023/api/auth/verify/" + verificationToken));

        user = userRepository.save(user);
        return user;
    }

    public boolean userVerify(User user){
        if(user.getCreateExpiry().isBefore(Instant.now())){
            return false;
        }
        user.setCreateToken("");
        user.setEnabled(true);
        userRepository.save(user);

        return true;
    }
}
