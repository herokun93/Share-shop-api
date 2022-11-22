package share.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import share.shop.exceptions.ResourceNotFoundException;
import share.shop.models.Role;
import share.shop.models.RoleName;
import share.shop.models.User;
import share.shop.payloads.RoleUserRequest;
import share.shop.services.RoleService;
import share.shop.services.UserService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/roles")
    public ResponseEntity getAll(){
        return ResponseEntity.ok(roleService.findAll());
    }

    @PostMapping(value = "/roles/user")
    public ResponseEntity addRoleForUser(
            @RequestBody  @Valid RoleUserRequest roleUserRequest){
        String email = roleUserRequest.getEmail();
        String role = roleUserRequest.getRole();

        Role roleEntity = roleService.findByName(RoleName.valueOf(role)).orElseThrow(()->
                new ResourceNotFoundException("Role","name",role));

        User user = userService.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User","email",email));


        if(user.getRoles().contains(role)){
            return ResponseEntity.ok(null);
        }else {
            user.getRoles().add(roleEntity);
            userService.saveAndFlush(user);
        }

        return ResponseEntity.ok(user.getRoles());
    }

    @DeleteMapping(value = "/roles/user")
    public ResponseEntity deleteRoleForUser(
            @RequestBody  @Valid RoleUserRequest roleUserRequest){
        String email = roleUserRequest.getEmail();
        String role = roleUserRequest.getRole();

        Role roleEntity = roleService.findByName(RoleName.valueOf(role)).orElseThrow(()->
                new ResourceNotFoundException("Role","name",role));

        User user = userService.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User","email",email));


        if(user.getRoles().contains(role)){

            System.out.println("Remove role");

            Set<Role> roles = user.getRoles();
            roles.remove(roleEntity);

            user.setRoles(roles);

            userService.saveAndFlush(user);
            return ResponseEntity.ok(user.getRoles());
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "/roles/user/{email}")
    public ResponseEntity getRoleForUser(
            @PathVariable("email") @Min(0) String email) {

        User user = userService.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User","email",email));
        return ResponseEntity.ok(user.getRoles());
    }




}
