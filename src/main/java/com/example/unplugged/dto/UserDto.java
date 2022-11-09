package com.example.unplugged.dto;

import com.example.unplugged.domain.entity.UserEntity;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {

    private String id;
    private String pw;
    private String name;
    private String phone;
    private String studentNum;
    private String major;
    private String year;
    private String session;
    private String position;
    private String gender;
    private String birthday;
    private String state;
    private LocalDate date;

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
                .birthday(birthday)
                .build();
    }

    @Builder
    public UserDto(String id , String pw , String name , String phone , String studentNum , String major , String year , String session , String position , String gender , String state , String birthday , LocalDate date) {
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
        this.birthday = birthday;
        this.state = state;
        this.date = date;

    }
}