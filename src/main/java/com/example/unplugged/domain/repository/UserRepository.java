package com.example.unplugged.domain.repository;

import com.example.unplugged.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
        Optional<UserEntity> findById(Long userId);
}