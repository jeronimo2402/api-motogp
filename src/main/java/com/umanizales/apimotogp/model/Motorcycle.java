package com.umanizales.apimotogp.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Motorcycle {
    @Id
    @Column(name = "numMotorcycle",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long numMotorcycle;

    @Basic
    @Column(name = "color",nullable = false)
    private String color;

    @Basic
    @Column(name = "state",nullable = false)
    private boolean state;

    @Basic
    @Column(name = "pilot",nullable = false)
    private String pilot;

    @OneToMany(mappedBy = "motorcycle")
    private List<ClassificationTimes> classificationTimesList;



}
