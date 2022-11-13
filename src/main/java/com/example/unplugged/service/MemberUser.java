package com.example.unplugged.service;

import com.example.unplugged.domain.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class MemberUser extends User {

    private UserEntity userEntity;

    public MemberUser(String id, String pw, Collection<? extends GrantedAuthority> authorities, UserEntity userEntity) {
        super(id, pw, authorities);
        this.userEntity = userEntity;
    }

    public UserEntity getUserEntity() {

        return userEntity;
    }
}
