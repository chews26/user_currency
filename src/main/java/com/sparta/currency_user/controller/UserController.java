package com.sparta.currency_user.controller;

import com.sparta.currency_user.common.Const;
import com.sparta.currency_user.dto.user.LoginRequestDto;
import com.sparta.currency_user.dto.user.UserRequestDto;
import com.sparta.currency_user.dto.user.UserResponseDto;
import com.sparta.currency_user.exception.CustomException;
import com.sparta.currency_user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 모든 유저 조회
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findUsers() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    // 특정 유저 조회
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findUser(@PathVariable Long id
    ) throws CustomException {
        return ResponseEntity.ok().body(userService.findById(id));
    }

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> createUser(
            @Valid
            @RequestBody UserRequestDto userRequestDto
    ) throws CustomException {
        return ResponseEntity.ok().body(userService.signUp(userRequestDto));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(
            @Valid
            @RequestBody LoginRequestDto loginRequestDto,
            HttpServletRequest request
    ) throws CustomException {
        UserResponseDto userResponse = userService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        HttpSession session = request.getSession(true);
        session.setAttribute(Const.LOGIN_USER, loginRequestDto.getEmail());
        return ResponseEntity.ok(userResponse);
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok("로그아웃 완료");
    }

    // 회원 탈퇴
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) throws CustomException {
        userService.deleteUserById(id);
        return ResponseEntity.ok().body("정상적으로 삭제되었습니다.");
    }
}