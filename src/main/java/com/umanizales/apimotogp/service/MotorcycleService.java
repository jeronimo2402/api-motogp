package com.umanizales.apimotogp.service;

import com.umanizales.apimotogp.model.Motorcycle;
import com.umanizales.apimotogp.repository.MotorcycleRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Data
public class MotorcycleService {
    @Autowired
    private MotorcycleRepository motorcycleRepository;

    public List<Motorcycle> getMotorcycles() {
        return motorcycleRepository.findAll();

    }

    public Motorcycle saveMotorcycle(Motorcycle motorcycle){
        return motorcycleRepository.save(motorcycle);
    }

    public String deleteMotorcycle(Motorcycle motorcycle){
        motorcycleRepository.delete(motorcycle);
        return "motorcycle deleted";
    }

    public Motorcycle getMotorcycle(String numMotorcycle){
        return motorcycleRepository.findById(numMotorcycle).get();
    }

}
