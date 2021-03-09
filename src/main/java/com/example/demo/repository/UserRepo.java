package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserPo, Long> {
    Optional<UserPo> findFirstByNameIgnoreCase(String name);

    void deleteByNameIgnoreCase(String name);
}
