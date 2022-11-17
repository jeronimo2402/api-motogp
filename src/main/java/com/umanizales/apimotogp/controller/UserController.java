package com.umanizales.apimotogp.controller;


import com.umanizales.apimotogp.model.Role;
import com.umanizales.apimotogp.model.User;
import com.umanizales.apimotogp.model.dto.UserDTO;
import com.umanizales.apimotogp.model.dto.UserRoleDTO;
import com.umanizales.apimotogp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path="roles")
    public List<Role> getRoles(){return userService.getRoleList();}

    @PostMapping(path="saverole")
    public Role saveRole(@RequestBody Role role){return userService.saveRole(role);}

    @PostMapping(path="deleterole")
    public String deleteRole(@RequestBody Role role){return userService.deleteRole(role);}

    @GetMapping
    public List<User> getUsers(){
        return userService.getList();
    }

    @PostMapping(path="save")
    public User saveUser(@RequestBody UserRoleDTO userRoleDTO){
        User user = new User(userRoleDTO.getEmail(), userRoleDTO.getPassword(),
                userService.findRole(userRoleDTO.getRole_code()));
        return userService.saveUser(user);
    }

    @PostMapping(path="delete")
    public String deleteUser(@RequestBody UserRoleDTO userRoleDTO){
        User user = new User(userRoleDTO.getEmail(), userRoleDTO.getPassword(),
                userService.findRole(userRoleDTO.getRole_code()));
        return userService.deleteUser(user);
    }

    @PostMapping(path="choose")
    public User chooseUser(@RequestBody UserDTO userDTO){
       return userService.chooseUser(userDTO.getEmail(),userDTO.getPassword());
    }
}
