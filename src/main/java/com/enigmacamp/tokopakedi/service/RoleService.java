package com.enigmacamp.tokopakedi.service;

import com.enigmacamp.tokopakedi.dto.ERole;
import com.enigmacamp.tokopakedi.entity.Role;

public interface RoleService {
    Role getOrSave(ERole role);
}
