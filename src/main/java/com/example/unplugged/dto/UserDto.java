package com.example.unplugged.dto;

import com.example.unplugged.domain.entity.UserEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String pw;
    private String name;
    private String phone;
    private String studentNum;
    private String major;
    private String year;
    private String session;
    private String position;
    private String gender;
    private String state;
    private LocalDateTime Date;

    public UserEntity toEntity(){
        return UserEntity.builder()
                .id(id)
                .pw(pw)
                .name(name)
                .phone(phone)
                .studentNum(studentNum)
                .major(major)
                .year(year)
                .session(session)
                .position(position)
                .gender(gender)
                .state(state)
                .build();
    }

    @Builder
    public UserDto(Long id , String pw , String name , String phone , String studentNum , String major , String year , String session , String position , String gender , String state) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.phone = phone;
        this.studentNum = studentNum;
        this.major = major;
        this.year = year;
        this.session = session;
        this.position = position;
        this.gender = gender;
        this.state = state;

    }
}