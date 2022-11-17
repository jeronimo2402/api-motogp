package com.umanizales.apimotogp.model;


import com.umanizales.apimotogp.model.dto.PilotsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Race {
    private Classification classification;
    private String state;
    private ListDE listDE;
    private List<PilotsDTO> pilotsFelt;

    public void setState(String state) {
        this.state = state;
    }



}
