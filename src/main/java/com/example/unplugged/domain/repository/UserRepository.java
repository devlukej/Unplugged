package com.example.unplugged.domain.repository;

import com.example.unplugged.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {
        Optional<UserEntity> findById(String id);

}