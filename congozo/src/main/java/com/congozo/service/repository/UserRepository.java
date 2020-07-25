package com.congozo.service.repository;

import com.congozo.service.model.CongozoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<CongozoUser, Long> {
    Optional<CongozoUser> findByEmail(String email);

    Boolean existsByEmail(String email);

    Boolean existsByMobileNumber(String mobuleNumber);
}
