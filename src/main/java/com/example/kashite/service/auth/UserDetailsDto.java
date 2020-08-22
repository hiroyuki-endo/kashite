package com.example.kashite.service.auth;

import com.example.kashite.query.model.account.AccountEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserDetailsDto implements UserDetails, CredentialsContainer {

    private String id;
    private String password;
    private String username;
    private Set<GrantedAuthority> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    public UserDetailsDto(AccountEntity account) {
        this.id = account.getId();
        this.password = account.getPassword();
        this.username = account.getName();
        this.authorities = new HashSet<>();
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }


    @Override
    public void eraseCredentials() {

    }
}
