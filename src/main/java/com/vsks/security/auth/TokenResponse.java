package com.vsks.security.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class TokenResponse {

    private String accessToken;
    private Long expiryInMillis;
}
