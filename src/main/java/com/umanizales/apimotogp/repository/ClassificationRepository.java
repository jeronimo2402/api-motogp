package com.umanizales.apimotogp.repository;

import com.umanizales.apimotogp.model.Classification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassificationRepository extends JpaRepository<Classification,Long> {
}
