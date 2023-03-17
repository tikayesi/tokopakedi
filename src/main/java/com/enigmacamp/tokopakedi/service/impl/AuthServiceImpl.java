package com.enigmacamp.tokopakedi.service.impl;

import com.enigmacamp.tokopakedi.dto.*;
import com.enigmacamp.tokopakedi.entity.Role;
import com.enigmacamp.tokopakedi.entity.User;
import com.enigmacamp.tokopakedi.repository.UserRepository;
import com.enigmacamp.tokopakedi.security.JwtUtils;
import com.enigmacamp.tokopakedi.service.AuthService;
import com.enigmacamp.tokopakedi.service.RoleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private JwtUtils jwtUtils;
    private RoleService roleService;


    @Override
    public UserDTO register(AuthRequest user) {
        Role role = roleService.getOrSave(ERole.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User userSaved = userRepository.save(new User(null, user.getUsername(), user.getPassword(),
                new ArrayList<>(Collections.singleton(role))));
        return new UserDTO(userSaved);
    }

    @Override
    public LoginDTO login(AuthRequest user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String token = jwtUtils.generateTokenFromUsername(userDetails.getUsername());
        return new LoginDTO(userDetails, token);
    }
}
