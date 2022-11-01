package com.example.unplugged.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "user_info")
public class UserEntity {

    @Id
    @Column(length = 50, nullable = false)
    private Long id;

    @Column(length = 50, nullable = false)
    private String pw;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 20, nullable = false)
    private String phone;

    @Column(length = 20, nullable = false)
    private String studentNum;

    @Column(length = 20, nullable = false)
    private String major;

    @Column(length = 20, nullable = false)
    private String year;

    @Column(length = 20, nullable = false)
    private String session;

    @Column(length = 2, nullable = false)
    private String position;

    @Column(length = 2, nullable = false)
    private String gender;

   // @Column(length = 100, nullable = false)
  //  private String date;

 //   @Column(length = 100, nullable = false)
 //   private String profile;

//    @Column(length = 100, nullable = false)
//    private String birthday;

    @Column(length = 2, nullable = false)
    private String state;




    @Builder
    public UserEntity(Long id , String pw , String name , String phone , String studentNum , String major , String year , String session , String position , String gender , String state) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.phone = phone;
        this.studentNum =studentNum;
        this.major = major;
        this.year = year;
        this.session = session;
        this.position = position;
        this.gender = gender;
        this.state = state;

    }
}