package com.sparta.currency_user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CustomErrorCode {

    // 유저 메서드
    EMAIL_INCORRECT("U0001", "이메일이 일치하지 않습니다."),
    EMAIL_NOT_FOUND("U0002", "존재하지 않는 이메일입니다."),
    PASSWORD_INCORRECT("U0003", "비밀번호가 일치하지 않습니다."),
    USER_DEACTIVATED("U0004", "이미 회원탈퇴 처리 되었습니다."),
    USER_NOT_FOUND("U0005", "존재하지 않는 회원입니다."),
    USER_ALREADY_EXISTS("U0006", "이미 존재하는 사용자입니다."),

    // 환전 메서드
    COMMENT_NOT_FOUND("C0001", "잘못된 입력값입니다."),
    POST_FORBIDDEN("C0002", "게시글에 대한 접근 권한이 없습니다."),
    CURRENCY_INCORRECT("C0003", "해당 통화를 찾을 수 없습니다.");

    private final String code;
    private final String message;
}
