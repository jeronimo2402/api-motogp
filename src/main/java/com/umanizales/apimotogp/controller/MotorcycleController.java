package com.umanizales.apimotogp.controller;

import com.umanizales.apimotogp.model.Motorcycle;
import com.umanizales.apimotogp.service.MotorcycleService;
import com.umanizales.apimotogp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path="motorcycle")
public class MotorcycleController {
    @Autowired
    private MotorcycleService motorcycleService;
    @Autowired
    private UserService userService;

    @GetMapping
    public List<Motorcycle> getMotorcycles(){
        if(Objects.equals(userService.getCurrentUser().getRole().getCode(),"1001")){
            return motorcycleService.getMotorcycles();
        }
        return null;
    }

    @PostMapping(path="/save")
    public String saveMotorcycle(@RequestBody Motorcycle motorcycle){
        if(Objects.equals(userService.getCurrentUser().getRole().getCode(),"1001")) {
            motorcycleService.saveMotorcycle(motorcycle);
            return "Motorcycle saved";
        }
        return "User must be administrator";
    }

    @PostMapping(path="/delete")
    public String deleteMotorcycle(Motorcycle motorcycle){
        if(Objects.equals(userService.getCurrentUser().getRole().getCode(),"1001")) {
            motorcycleService.deleteMotorcycle(motorcycle);
            return "Motorcycle deleted";
        }
        return "User must be administrator";
    }

    @PostMapping(path="/search")
    public Motorcycle findMotorcycle(String numMotorcycle) {
        if (Objects.equals(userService.getCurrentUser().getRole().getCode(), "1001")) {
            return motorcycleService.getMotorcycle(numMotorcycle);
        }
        return null;
    }
}
