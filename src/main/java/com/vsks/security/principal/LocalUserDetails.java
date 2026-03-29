package com.vsks.security.principal;

import com.vsks.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class LocalUserDetails implements UserDetails {

    private String username;
    private String password;
    private String name;
    private Collection<? extends GrantedAuthority> authorities;
    private boolean enabled;

    public LocalUserDetails(User user) {
        this.username = user.getEmailId();
        this.password = user.getPassword();
        this.name = user.getName();
        this.authorities = Arrays.stream(user.getAuthorities().split(",")).map(SimpleGrantedAuthority::new).toList();
        this.enabled = user.isActive();
    }
}
