package com.enigmacamp.tokopakedi.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class LoginDTO {
    private String username;
    private List<String> roles;
    private String token;

    public LoginDTO(UserDetailsImpl user, String token) {
        this.username = user.getUsername();
        this.roles = user.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        this.token = token;
    }
}
