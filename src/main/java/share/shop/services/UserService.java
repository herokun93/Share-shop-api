package share.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import share.shop.models.User;
import share.shop.repositories.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    UserRepository userRepository;

    private final MailService mailService;


    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }
}
