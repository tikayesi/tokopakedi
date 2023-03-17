package com.enigmacamp.tokopakedi.dto;

import com.enigmacamp.tokopakedi.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserDTO {
    private String username;
    private List<String> roles;

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.roles = user.getRoles().stream().map(role -> role.getRole().name())
                .collect(Collectors.toList());
    }
}
