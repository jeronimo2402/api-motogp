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
public class Role {
    @Id
    @Column(name = "code", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String code;

    @Basic
    @Column(name = "description", nullable = false, length = 50)
    private String description;

    @OneToMany(mappedBy = "role")
    private List<User> users;
}
