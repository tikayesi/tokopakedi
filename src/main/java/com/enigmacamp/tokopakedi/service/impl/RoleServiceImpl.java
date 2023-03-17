package com.enigmacamp.tokopakedi.service.impl;

import com.enigmacamp.tokopakedi.dto.ERole;
import com.enigmacamp.tokopakedi.entity.Role;
import com.enigmacamp.tokopakedi.repository.RoleRepository;
import com.enigmacamp.tokopakedi.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role getOrSave(ERole role) {
        return roleRepository.findByRole(role)
                .orElseGet(() -> roleRepository.save(new Role(null, role)));
    }
}
