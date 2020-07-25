package com.congozo.service.security;

import com.congozo.service.model.CongozoUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component("Congozouser")
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    private CongozoUser CongozoUser;

    public UserDetailsImpl(){

    }

    public UserDetailsImpl(Long id, String email, String password,
                           Collection<? extends GrantedAuthority> authorities, CongozoUser CongozoUser) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.CongozoUser = CongozoUser;
    }

    public static UserDetailsImpl build(CongozoUser CongozoUser) {
        List<GrantedAuthority> authorities = CongozoUser.getCongozoRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
        return new UserDetailsImpl(
                CongozoUser.getId(),
                CongozoUser.getEmail(),
                CongozoUser.getPassword(),
                authorities,
                CongozoUser);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    public CongozoUser getCongozoUser() {
        return CongozoUser;
    }

    public void setCongozoUser(CongozoUser congozoUser) {
        this.CongozoUser = congozoUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}
