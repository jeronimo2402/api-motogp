package com.umanizales.apimotogp.model.dto;

import com.umanizales.apimotogp.model.Motorcycle;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PilotsDTO {
    private Motorcycle motorcycle;
    private int position;
}
