package com.example.unplugged.dto;

import com.example.unplugged.domain.entity.UserEntity;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {

    //    @NotBlank(message = "아이디는 필수 입력 값입니다.")
//    @Pattern(regexp = "^[a-z\\d]{4,20}$", message = "아이디는 영어 소문자와 숫자만 사용하여 4~20자리여야 합니다.")
    private String id;
    //    @NotBlank(message = "패스워드는 필수 입력 값입니다.")
//    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$", message = "비밀번호는 8~16자리수여야 합니다. 영문 대소문자, 숫자, 특수문자를 1개 이상 포함해야 합니다.")
    private String pw;
    //    @Pattern(regexp = "^[ㄱ-힣]{3,8}$", message = "이름은 한글이여야 합니다.")
//    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;
    //    @Pattern(regexp = "^[1-9]{11}$", message = "연락처는 11자리수여야 합니다.")
//    @NotBlank(message = "연락처는 필수 입력 값입니다.")
    private String phone;
    //    @Pattern(regexp = "^[1-9]{8}$", message = "학번은 8자리수여야 합니다.")
//    @NotBlank(message = "학번은 필수 입력 값입니다.")
    private String studentNum;
    //    @NotBlank(message = "학과는 필수 입력 값입니다.")
    private String major;
    //    @NotBlank(message = "기수는 필수 입력 값입니다.")
    private String year;
    //    @NotBlank(message = "세션은 필수 입력 값입니다.")
    private String session;
    //    @NotBlank(message = "직급은 필수 입력 값입니다.")
    private String position;
    //    @NotBlank(message = "성별은 필수 입력 값입니다.")
    private String gender;
    //    @NotBlank(message = "생년월일은 필수 입력 값입니다.")
    private String birthday;

    private String state;
    private LocalDate date;

    private String filePath;

    private String imgFullPath;

    public UserEntity toEntity() {
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
                .filePath(filePath)
                .build();
    }

    @Builder
    public UserDto(String id, String pw, String name, String phone, String studentNum, String major, String year, String session, String position, String gender, String state, String birthday, LocalDate date , String filePath , String imgFullPath) {
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
        this.filePath = filePath;
        this.imgFullPath = imgFullPath;
    }
}