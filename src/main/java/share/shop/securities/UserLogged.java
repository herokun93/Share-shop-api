package share.shop.securities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import share.shop.controllers.UserController;
import share.shop.models.Role;
import share.shop.models.User;
import share.shop.repositories.UserRepository;

import java.util.Collection;
import java.util.Optional;

@Getter
@Setter
public class UserLogged {

    Object principal;

    private String username;
    private Long id;
    Optional<User> user;

    @Autowired
    private UserRepository userRepository;


    public UserLogged() {
        principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        user = userRepository.findByEmail(username);
        if(user.isPresent()) this.id = user.get().getId();
    }
    boolean isAdmin(){
        Collection<Role> roles = user.get().getRoles();
        roles.forEach(role->{

        });
        return true;
    }
}
