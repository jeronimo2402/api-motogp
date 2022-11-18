package com.umanizales.apimotogp.model;


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
    private long code;

    @Basic
    @Column(name = "description", nullable = false, length = 50)
    private String description;

    @OneToMany(mappedBy = "classification")
    private List<ClassificationTimes> grill;

    @Basic
    @Column(name = "state",nullable = false)
    private boolean state;
}
