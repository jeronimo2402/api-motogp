package com.umanizales.apimotogp.model.dto;


import com.umanizales.apimotogp.model.Classification;
import com.umanizales.apimotogp.model.Motorcycle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassificationDTO {
    @Id
    @OneToOne
    @Column(name = "motorcycle",nullable = false)
    private Motorcycle motorcycle;

    @Basic
    @Column(name = "time", nullable = false)
    private float time;

    @ManyToOne
    @JoinColumn(name = "classification_time", referencedColumnName = "code", nullable = false)
    private Classification classification;

}
