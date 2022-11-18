package com.umanizales.apimotogp.repository;

import com.umanizales.apimotogp.model.ClassificationTimes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassificationTimesRepository extends JpaRepository<ClassificationTimes,Long> {
}
