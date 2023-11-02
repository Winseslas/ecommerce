package com.gdtcore.securityservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
public class AuthenticationResponse {
    private String jwt;
}
