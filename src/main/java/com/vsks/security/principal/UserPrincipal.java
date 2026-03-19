package com.vsks.security.principal;

import com.vsks.dto.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class UserPrincipal implements UserDetails {

    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private boolean active = true;

    public UserPrincipal(User user) {
        this.username = user.getEmailId();
        this.password = user.getPassword();
        this.authorities = user.getAuthorities().stream().map(it -> new SimpleGrantedAuthority("ROLE_" + it)).collect(Collectors.toList());
        this.active = user.isActive();
    }
}
