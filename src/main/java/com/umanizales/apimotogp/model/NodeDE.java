package com.umanizales.apimotogp.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class NodeDE {
    private Motorcycle data;
    private NodeDE previous;
    private NodeDE next;

    public NodeDE(Motorcycle data) {
        this.data = data;
    }
}
