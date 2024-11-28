package com.sparta.currency_user.service;

import com.sparta.currency_user.config.PasswordEncoder;
import com.sparta.currency_user.dto.user.UserRequestDto;
import com.sparta.currency_user.dto.user.UserResponseDto;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.exception.CustomErrorCode;
import com.sparta.currency_user.exception.CustomException;
import com.sparta.currency_user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    // 특정 유저 조회
    public UserResponseDto findById(Long id) throws CustomException {
        return new UserResponseDto(findUserById(id));
    }
        public User findUserById(Long id) throws CustomException {
        return userRepository.findById(id)
                .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));
    }

    // 모든 유저 조회
    public List<UserResponseDto> findAll() {
        return userRepository.findAll().stream().map(UserResponseDto::toDto).toList();
    }

    // 회원가입
    @Transactional
    public UserResponseDto signUp(UserRequestDto userRequestDto) throws CustomException {

        // 이메일 중복 여부 확인
        if (userRepository.existsByEmail(userRequestDto.getEmail())) {
            throw new CustomException(CustomErrorCode.USER_ALREADY_EXISTS);
        }

        String encodedPassword = passwordEncoder.encode(userRequestDto.getPassword());
        User user = new User(userRequestDto.getName(), userRequestDto.getEmail(), encodedPassword);
        User saveUser = userRepository.save(user);
        return new UserResponseDto(user.getId(), user.getName(), user.getEmail());
    }

    // 로그인
    public UserResponseDto login(String email, String password) {
        // 아이디가 존재하는지 확인
        User user = userRepository.findByEmailOrElseThrow(email);
        // 비밀번호가 일치하는지 확인
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "비밀번호가 잘못되었습니다.");
        }
        return new UserResponseDto(user.getId(), user.getName(), user.getEmail());
    }

    // 유저 삭제
    @Transactional
    public void deleteUserById(Long id) throws CustomException {
        this.findUserById(id);
        userRepository.deleteById(id);
    }

}
