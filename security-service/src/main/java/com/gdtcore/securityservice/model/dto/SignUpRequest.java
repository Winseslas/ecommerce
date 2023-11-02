package com.gdtcore.securityservice.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class SignUpRequest {
    private String email;
    private String username;
    private String password;
}
