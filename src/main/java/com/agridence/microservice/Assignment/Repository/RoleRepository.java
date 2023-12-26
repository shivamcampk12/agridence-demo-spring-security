package com.agridence.microservice.Assignment.Repository;

import com.agridence.microservice.Assignment.Entity.Role;
import com.agridence.microservice.Assignment.Entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRoleName(RoleName roleName);


}
