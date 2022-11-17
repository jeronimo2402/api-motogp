package com.umanizales.apimotogp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PilotTimesDTO {
    private String numMotorcycle;
    private float time;
    private String classification_code;
}
