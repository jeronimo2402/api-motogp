package com.umanizales.apimotogp.model;


import com.umanizales.apimotogp.model.dto.ClassificationDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Classification {
    @Id
    @Column(name = "code", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String code;

    @Basic
    @Column(name = "description", nullable = false, length = 50)
    private String description;

    @OneToMany(mappedBy = "classification")
    private List<ClassificationDTO> grill;

    @Basic
    @Column(name = "state",nullable = false)
    private boolean state;
}
