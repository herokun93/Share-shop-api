package share.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import share.shop.controllers.RoleController;
import share.shop.models.Role;
import share.shop.models.RoleName;
import share.shop.repositories.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Optional<Role> findById(RoleName roleName){return roleRepository.findById(roleName);}
    public Optional<Role> findByName(RoleName roleName){return roleRepository.findByName(roleName);}
    public List<Role> findAll(){return roleRepository.findAll();};
}
