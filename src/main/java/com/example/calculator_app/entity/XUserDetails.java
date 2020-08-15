package com.example.calculator_app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Data
@Service
@NoArgsConstructor
@AllArgsConstructor
public class XUserDetails implements UserDetails {

    private long id;
    private String password;
    private String username;
    private String fullname;
    private String[] roles;

    public XUserDetails(Long id, String password, String username, String fullname, String... roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.roles = roles;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(roles)
                .map(s -> "ROLE_"+ s)
                .map(s -> (GrantedAuthority) () -> s)
                .collect(Collectors.toList());
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
