package share.shop.securities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import share.shop.controllers.UserController;
import share.shop.models.Role;
import share.shop.models.User;
import share.shop.repositories.UserRepository;

import java.util.Collection;
import java.util.Optional;

@Getter
@Setter
public class UserLogged {

    Authentication auth;

    private long id;

    private String email;


    @Autowired
    private UserRepository userRepository;


    public UserLogged() {
        auth = SecurityContextHolder.getContext().getAuthentication();
        email = auth.getName();
    }
}
