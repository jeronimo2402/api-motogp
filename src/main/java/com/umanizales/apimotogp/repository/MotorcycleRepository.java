package com.umanizales.apimotogp.repository;

import com.umanizales.apimotogp.model.Motorcycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotorcycleRepository extends JpaRepository<Motorcycle,Long> {

}
