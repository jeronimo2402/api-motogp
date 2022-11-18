package com.umanizales.apimotogp.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassificationTimes {
    @Id
    @Column(name = "code", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long code;

    @ManyToOne
    @JoinColumn(name = "motorcycle", referencedColumnName = "numMotorcycle",nullable = false)
    private Motorcycle motorcycle;

    @Basic
    @Column(name = "time", nullable = false)
    private float time;

    @ManyToOne
    @JoinColumn(name = "classification_time", referencedColumnName = "code", nullable = false)
    private Classification classification;

}
