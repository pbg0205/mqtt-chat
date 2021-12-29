package com.example.chatsubject.account.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(Email email);
    Optional<User> findByEmail(Email email);
}
