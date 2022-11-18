package com.umanizales.apimotogp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PilotTimesDTO {
    private long code;
    private long numMotorcycle;
    private float time;
    private long classification_code;
}
