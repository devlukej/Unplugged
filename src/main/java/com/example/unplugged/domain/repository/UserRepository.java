package com.example.unplugged.domain.repository;

import com.example.unplugged.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {
        Optional<UserEntity> findById(String id);
        List<UserEntity> findAll();
        List<UserEntity> findByNameContaining(String nameKeyword);
        List<UserEntity> findByYearContaining(String yearKeyword);
        List<UserEntity> findBySessionContaining(String sessionKeyword);
        List<UserEntity> findByPositionContaining(String positionKeyword);



}