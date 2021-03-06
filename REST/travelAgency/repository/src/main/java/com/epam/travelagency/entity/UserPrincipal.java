package com.epam.travelagency.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {
    private Integer id;
    @Size(min = 4, max = 100)
    private String username;
    @Pattern(regexp = "(?=.*?[0-9])(?=.*?[a-z])(?=.*?[A-Z])\\w{6,20}")
    private String password;
    private List<GrantedAuthority> authorities;

    public UserPrincipal(User user) {
        this.id = user.getId();
        this.username = user.getLogin();
        this.password = user.getPassword();
        if (user.getIsAdmin() == 1) {
            authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("Admin");
        } else {
            authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("Member");
        }
    }

    public Integer getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
}
