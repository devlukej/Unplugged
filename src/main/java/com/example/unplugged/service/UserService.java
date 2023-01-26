package com.example.unplugged.service;

import com.example.unplugged.domain.Role;
import com.example.unplugged.domain.entity.UserEntity;
import com.example.unplugged.domain.repository.UserRepository;
import com.example.unplugged.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private UserRepository userRepository;

    private static final int BLOCK_PAGE_NUM_COUNT = 10; // 블럭에 존재하는 페이지 번호 수
    private static final int PAGE_POST_COUNT = 15; // 한 페이지에 존재하는 게시글 수

//    @javax.transaction.Transactional
//    public List<UserDto> getUserlist(Integer pageNum) {
//        Page<UserEntity> page = userRepository.findAll(PageRequest.of(pageNum - 1, PAGE_POST_COUNT, Sort.by(Sort.Direction.DESC, "date")));
//
//        List<UserEntity> userEntities = page.getContent();
//        List<UserDto> userDtoList = new ArrayList<>();
//
//        for (UserEntity userEntity : userEntities) {
//            UserDto userDTO = UserDto.builder()
//                    .id(userEntity.getId())
//                    .name(userEntity.getName())
//                    .phone(userEntity.getPhone())
//                    .year(userEntity.getYear())
//                    .session(userEntity.getSession())
//                    .position(userEntity.getPosition())
//                    .build();
//
//            userDtoList.add(userDTO);
//        }
//
//        return userDtoList;
//    }

    @javax.transaction.Transactional
    public List<UserDto> getPassUserlist(String state) {


        List<UserEntity> userEntities = userRepository.findByState("1");

        List<UserDto> userDtoList = new ArrayList<>();

        if (userEntities.isEmpty()) return userDtoList;


        for (UserEntity userEntity : userEntities) {
            userDtoList.add(this.convertEntityToDto(userEntity));
        }

        return userDtoList;
    }

    @javax.transaction.Transactional
    public List<UserDto> getJoinUserlist(String state) {


        List<UserEntity> userEntities = userRepository.findByState("0");

        List<UserDto> userDtoList = new ArrayList<>();

        if (userEntities.isEmpty()) return userDtoList;


        for (UserEntity userEntity : userEntities) {
            userDtoList.add(this.convertEntityToDto(userEntity));
        }

        return userDtoList;
    }

    @javax.transaction.Transactional
    public Long getUserCount() {
        return userRepository.count();
    }

    public Integer[] getPageList(Integer curPageNum) {
        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];

        // 총 게시글 갯수
        Double postsTotalCount = Double.valueOf(this.getUserCount());

        // 총 게시글 기준으로 계산한 마지막 페이지 번호 계산 (올림으로 계산)
        Integer totalLastPageNum = (int) (Math.ceil((postsTotalCount / PAGE_POST_COUNT)));

        // 현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)
                ? curPageNum + BLOCK_PAGE_NUM_COUNT
                : totalLastPageNum;

        // 페이지 시작 번호 조정
        Integer blockStartPageNum =
                (curPageNum <= BLOCK_PAGE_NUM_COUNT / 2)
                        ? 1
                        : curPageNum - BLOCK_PAGE_NUM_COUNT / 2;

        // 페이지 번호 할당
        for (int val = blockStartPageNum, idx = 0; val <= blockLastPageNum; val++, idx++) {
            pageList[idx] = val;
        }

        return pageList;
    }


    //이름검색
    @javax.transaction.Transactional
    public List<UserDto> searchUserName(String nameKeyword) {

        List<UserEntity> userEntities = userRepository.findByNameContaining(nameKeyword);


        List<UserDto> userDtoList = new ArrayList<>();

        if (userEntities.isEmpty()) return userDtoList;


        for (UserEntity userEntity : userEntities) {
            userDtoList.add(this.convertEntityToDto(userEntity));
        }

        return userDtoList;
    }

    //기수검색
    @javax.transaction.Transactional
    public List<UserDto> searchUserYear(String yearKeyword) {

        List<UserEntity> userEntities = userRepository.findByYearContaining(yearKeyword);


        List<UserDto> userDtoList = new ArrayList<>();

        if (userEntities.isEmpty()) return userDtoList;


        for (UserEntity userEntity : userEntities) {
            userDtoList.add(this.convertEntityToDto(userEntity));
        }

        return userDtoList;
    }

    //세션검색
    @javax.transaction.Transactional
    public List<UserDto> searchUserSession(String sessionKeyword) {

        List<UserEntity> userEntities = userRepository.findBySessionContaining(sessionKeyword);


        List<UserDto> userDtoList = new ArrayList<>();

        if (userEntities.isEmpty()) return userDtoList;


        for (UserEntity userEntity : userEntities) {
            userDtoList.add(this.convertEntityToDto(userEntity));
        }

        return userDtoList;
    }

    //직책검색
    @javax.transaction.Transactional
    public List<UserDto> searchUserPosition(String positionKeyword) {

        List<UserEntity> userEntities = userRepository.findByPositionContaining(positionKeyword);


        List<UserDto> userDtoList = new ArrayList<>();

        if (userEntities.isEmpty()) return userDtoList;


        for (UserEntity userEntity : userEntities) {
            userDtoList.add(this.convertEntityToDto(userEntity));
        }

        return userDtoList;
    }

    private UserDto convertEntityToDto(UserEntity userEntity) {

        return UserDto.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .phone(userEntity.getPhone())
                .year(userEntity.getYear())
                .session(userEntity.getSession())
                .position(userEntity.getPosition())
                .filePath(userEntity.getFilePath())
                .imgFullPath("https://" + S3Service.CLOUD_FRONT_DOMAIN_NAME + "/" + userEntity.getFilePath())

                .build();
    }

    public void savePost(UserDto userDto) {
        userRepository.save(userDto.toEntity());
    }

    public List<UserDto> getList() {
        List<UserEntity> userEntityList = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();

        for (UserEntity userEntity : userEntityList) {
            userDtoList.add(convertEntityToDto(userEntity));
        }

        return userDtoList;
    }

    //비밀번호 암호화
    @Transactional
    public String joinUser(UserDto userDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userDto.setPw(passwordEncoder.encode(userDto.getPw()));
        userDto.setState("0");

        return userRepository.save(userDto.toEntity()).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Optional<UserEntity> userEntityWrapper = userRepository.findById(id);
        UserEntity userEntity = userEntityWrapper.get();

        if (userEntityWrapper == null) {
            throw new UsernameNotFoundException(id);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();


        if (("1").equals(userEntity.getState()) && ("간부").equals(userEntity.getPosition())) {
            authorities.add(new SimpleGrantedAuthority(Role.MANAGER.getValue()));
        } else if (("1").equals(userEntity.getState()) && ("동아리원").equals(userEntity.getPosition())) {
            authorities.add(new SimpleGrantedAuthority(Role.USER.getValue()));
        } else if (("2").equals(userEntity.getState())) {
            authorities.add(new SimpleGrantedAuthority(Role.GUEST.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.GUEST.getValue()));
        }

        return new MemberUser(userEntity.getId(), userEntity.getPw(), authorities, userEntity);
    }

}