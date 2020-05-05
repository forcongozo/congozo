package com.congozo.service.repository;

import com.congozo.service.model.Benutzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Benutzer, Long> {
    Optional<Benutzer> findByEmail(String email);

    Boolean existsByEmail(String email);

    Boolean existsByHandynummer(String handynummer);
}
