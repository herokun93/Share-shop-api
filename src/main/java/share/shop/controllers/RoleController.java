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
import java.util.List;
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
        String roleName = roleUserRequest.getRole();

        User user = userService.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User","email",email));

        Role role = roleService.findByName(RoleName.valueOf(roleName)).orElseThrow(()->
                new ResourceNotFoundException("Role","name",roleName));

        if(user.getRoles().contains(role)){

            userService.removeRoleUser(user.getId(),role.getId());
            return ResponseEntity.ok(null);
        }else {
            System.out.println("Remove bad");
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
