package com.enigmacamp.tokopakedi.service;

import com.enigmacamp.tokopakedi.dto.AuthRequest;
import com.enigmacamp.tokopakedi.dto.LoginDTO;
import com.enigmacamp.tokopakedi.dto.UserDTO;

public interface AuthService {
    UserDTO register(AuthRequest user);

    LoginDTO login(AuthRequest user);
}
