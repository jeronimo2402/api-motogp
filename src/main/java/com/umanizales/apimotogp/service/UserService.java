package com.umanizales.apimotogp.service;


import com.umanizales.apimotogp.model.Motorcycle;
import com.umanizales.apimotogp.model.Role;
import com.umanizales.apimotogp.model.User;
import com.umanizales.apimotogp.repository.RoleRepository;
import com.umanizales.apimotogp.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Data
public class UserService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    private User currentUser;

    public UserService(){
        this.currentUser = null;
    }
    public List<User> getList(){
        return userRepository.findAll();
    }
    public List<Role> getRoleList(){
        return roleRepository.findAll();
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public Role saveRole(Role role){
        return roleRepository.save(role);
    }

    public String deleteUser(User user){
        userRepository.delete(user);
        return "User deleted";
    }

    public String deleteRole(Role role){
        roleRepository.delete(role);
        return "Role deleted";
    }

    public Role findRole(String code){
        for (Role role:getRoleList()){
            if (Objects.equals(role.getCode(),code)){
                return role;
            }
        }
        return null;
    }
    public User chooseUser(String email, String password){
        for (User user:getList()){
            if (Objects.equals(user.getEmail(),email)&&Objects.equals(user.getPassword(),password)){
                this.currentUser = user;
                return user;
            }
        }
        return null;
    }
}
