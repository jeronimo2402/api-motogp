package com.umanizales.apimotogp.repository;

import com.umanizales.apimotogp.model.dto.ClassificationDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassificationDTORepository extends JpaRepository<ClassificationDTO,String> {
}
