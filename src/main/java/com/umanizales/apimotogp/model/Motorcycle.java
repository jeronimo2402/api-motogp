package com.umanizales.apimotogp.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Motorcycle {
    @Id
    @Column(name = "numMotorcycle")
    private String numMotorcycle;

    @Basic
    @Column(name = "color")
    private String color;

    @Basic
    @Column(name = "state")
    private boolean state;

    @Basic
    @Column(name = "pilot")
    private String pilot;
}
