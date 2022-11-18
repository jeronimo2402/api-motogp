package com.umanizales.apimotogp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRoleDTO {
    private String email;
    private String password;
    private long role_code;
}
