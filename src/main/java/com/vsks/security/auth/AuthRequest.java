package com.vsks.security.auth;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthRequest {

    private String emailId;
    private String password;
}
