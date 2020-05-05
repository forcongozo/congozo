package com.congozo.service.repository;

import com.congozo.service.model.ERole;
import com.congozo.service.model.CongozoRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<CongozoRole, Long> {
    Optional<CongozoRole> findByName(ERole name);
}
