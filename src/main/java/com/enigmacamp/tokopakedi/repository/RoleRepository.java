package com.enigmacamp.tokopakedi.repository;

import com.enigmacamp.tokopakedi.dto.ERole;
import com.enigmacamp.tokopakedi.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByRole(ERole role);
}
